package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.dbDto.PartOfFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.dbDto.feedListDbDto;
import GDSC.FirstProject.dto.requsetDto.UpdateFeedRequestDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.*;
import GDSC.FirstProject.repository.*;
import GDSC.FirstProject.service.FeedService;
import GDSC.FirstProject.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {

    public final MemberRepository memberRepository;
    public final CompanyRepository companyRepository;
    public final HashtagRepository hashtagRepository;
    public final FeedHashtagRepository feedHashtagRepository;
    public final FeedRepository feedRepository;
    public final S3Service s3Service;
    public final ConversionService conversionService;

    @Value("${cloud.aws.s3.url}")
    public String s3Url;

    @Override
    public String makeRandomS3Key() {
        return UUID.randomUUID().toString();
    }

    @Override
    public String concatRandomS3keyAndExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf('.');
        return fileName.substring(extensionIndex + 1);
    }

    @Override
    public Long saveFeed(createFeedRequestDto requestDto) {
        Member findMember = memberRepository.findById(Long.valueOf(requestDto.uid)).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        String RandomS3Key = makeRandomS3Key();

        RandomS3Key = RandomS3Key + "." + concatRandomS3keyAndExtension(requestDto.image_name);

        String url = s3Service.upload(requestDto.image, RandomS3Key);

        Company findCompany = companyRepository.findByvalue(requestDto.company).orElseThrow(() -> new IllegalArgumentException("회사가 존재하지 않습니다."));

        Feed saveFeed = feedRepository.save(new Feed(requestDto, RandomS3Key, findMember, findCompany));

        List<Feed> findMemberFeedList = findMember.getFeedList();
        findMemberFeedList.add(saveFeed);
        findMember.setFeedList(findMemberFeedList);

        for (String hashtag : requestDto.hashtags) {
            Hashtag findHashtag = hashtagRepository.findByvalue(hashtag).orElseThrow(() -> new IllegalArgumentException("해시태그가 존재하지 않습니다."));
            feedHashtagRepository.save(new FeedHashtag(saveFeed, findHashtag));
        }

        return saveFeed.getId();
    }
    @Override
    public feedInfoResponseDto findFeedInfo(Long id) {
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(id)
                .orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));

        return conversionService.convert(feedInfoDbDtos, feedInfoResponseDto.class);
    }

    @Override
    @Transactional
    public Long updateFeed(UpdateFeedRequestDto requestDto) {
        Feed feed = feedRepository.findById(Long.valueOf(requestDto.getUid()))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 피드입니다."));

        if (!requestDto.getCompany().equals("")){
            Company company = companyRepository.findByvalue(requestDto.getCompany())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
            feed.updateCompany(company);
        }

        if (!(requestDto.getHashtags().length == 0)) {
            List<Hashtag> hashtagList = new ArrayList<>();
            for (String hashtagValue: requestDto.getHashtags()) {
                Hashtag hashtag = hashtagRepository.findByvalue(hashtagValue)
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해시태그입니다."));
                hashtagList.add(hashtag);
            }
            feed.updateHashtag(hashtagList);
        }

        feed.update(requestDto);

        return feed.getId();
    }

    @Override
    public void deleteFeedById(Long id) {
        List<feedInfoDbDto> findFeed = feedRepository.findFeedInfo(id).orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));
        feedRepository.deleteById(id);
        s3Service.delete(findFeed.get(0).getS3_key());
    }

    @Override
    public Long translatePeopleCount(String people_count) {
        return people_count.equals("") ? null : Long.valueOf(people_count);
    }
    @Override
    public feedListResponseDto makeFeedListResponseDto(String company_name, Long people_count, List<String> hashtags, Long page_number) {
        PageRequest pageRequest;
        String[] hashtagArray = hashtags.toArray(new String[0]);

        pageRequest = PageRequest.of( page_number.intValue(), 5, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<feedListDbDto> feedListQuerydslFixed = feedRepository.findFeedList_Querydsl(company_name, people_count, hashtagArray, pageRequest);
        pageRequest = PageRequest.of( page_number.intValue(), 50, Sort.by(Sort.Direction.DESC, "createdDate"));

        List<PartOfFeedListDbDto> partOfFeedListQuerydslTest = feedRepository.findPartOfFeedList_Querydsl(company_name, people_count, hashtagArray);

        List<feedListDbDto> feedListDbDtos = feedListQuerydslFixed.getContent();

        distinctFeedListDbDto[] result = new distinctFeedListDbDto[feedListDbDtos.size()];
        distinctFeedListDbDto temp;
        Map<Long, Set<String>> map = new HashMap<>();
        for (PartOfFeedListDbDto dto : partOfFeedListQuerydslTest) {
            Long feedId = dto.getFeed_id();
            String hashtag = dto.getHashtag();
            if (map.containsKey(feedId)) {
                map.get(feedId).add(hashtag);
            } else {
                Set<String> hashtagsSet = new HashSet<>();
                hashtagsSet.add(hashtag);
                map.put(feedId, hashtagsSet);
            }
        }

        for (int i = 0; i < feedListDbDtos.size(); i++) {
            temp = new distinctFeedListDbDto(
                    feedListDbDtos.get(i).feed_id,
                    s3Url + feedListDbDtos.get(i).image,
                    feedListDbDtos.get(i).people_count,
                    feedListDbDtos.get(i).company_name,
                    map.get(feedListDbDtos.get(i).getFeed_id())
                            .toArray(new String[map.get(feedListDbDtos.get(i).getFeed_id()).size()]));
            result[i] = temp;
        }

        return feedListResponseDto.builder()
                .feedList(result)
                .page_number(page_number)
                .hasNext(feedListQuerydslFixed.hasNext())
                .build();

    }
}