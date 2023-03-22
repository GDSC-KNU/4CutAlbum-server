package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.dbDto.originalFeedListDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.*;
import GDSC.FirstProject.repository.*;
import GDSC.FirstProject.s3.S3Uploader;
import GDSC.FirstProject.service.FeedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedServiceImpl implements FeedService {

    public final MemberRepository memberRepository;
    public final CompanyRepository companyRepository;
    public final HashtagRepository hashtagRepository;
    public final FeedHashtagRepository feedHashtagRepository;
    public final FeedRepository feedRepository;
    public final S3Uploader s3Uploader;
    public final ConversionService conversionService;

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
    public String saveFeed(createFeedRequestDto requestDto) {
        Member findMember = memberRepository.findById(Long.valueOf(requestDto.uid)).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        String RandomS3Key = makeRandomS3Key();

        RandomS3Key = RandomS3Key+ "." +concatRandomS3keyAndExtension(requestDto.image_name);

        String url = s3Uploader.upload(requestDto.image, RandomS3Key);

        Company findCompany = companyRepository.findByvalue(requestDto.company).orElseThrow(() -> new IllegalArgumentException("회사가 존재하지 않습니다."));

        Feed saveFeed = feedRepository.save(new Feed(requestDto, RandomS3Key, findMember, findCompany));

        for (String hashtag : requestDto.hashtags) {
            Hashtag findHashtag = hashtagRepository.findByvalue(hashtag).orElseThrow(() -> new IllegalArgumentException("해시태그가 존재하지 않습니다."));
            feedHashtagRepository.save(new FeedHashtag(saveFeed, findHashtag));
        }

        return url;
    }

    @Override
    public feedListResponseDto findFeedList_Querydsl(String company_name, Long people_count, List<String> hashtagsListVer, Long page_number) {
        String[] hashtags = hashtagsListVer != null  ? hashtagsListVer.toArray(new String[hashtagsListVer.size()]) : null;
        PageRequest pageRequest = PageRequest.of(page_number.intValue(), 10, Sort.by(Sort.Direction.DESC, "createdDate"));

//        Slice<originalFeedListDbDto> slice = feedRepository.findFeedList(company_name, people_count, hashtags, pageRequest);
        Slice<originalFeedListDbDto> slice = feedRepository.findFeedList_Querydsl(company_name, people_count, hashtags, pageRequest);
        boolean hasNext = slice.hasNext();
        Long number = (long) slice.getNumber();

        List<originalFeedListDbDto> feedListBeforeConversion = slice.getContent();
        distinctFeedListDbDto[] feedListAfterConversion = conversionService.convert(feedListBeforeConversion
                .toArray(new originalFeedListDbDto[feedListBeforeConversion.size()]), distinctFeedListDbDto[].class);

        return feedListResponseDto.builder()
                .feedList(feedListAfterConversion)
                .page_number(number)
                .hasNext(hasNext)
                .build();
    }

    @Override
    public feedInfoResponseDto findFeedInfo(Long id) {
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(id)
                .orElseThrow(() -> new IllegalArgumentException("피드가 존재하지 않습니다."));

        return conversionService.convert(feedInfoDbDtos, feedInfoResponseDto.class);
    }


}