package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.*;
import GDSC.FirstProject.repository.*;
import GDSC.FirstProject.s3.S3Uploader;
import GDSC.FirstProject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {

    public final MemberRepository memberRepository;
    public final CompanyRepository companyRepository;
    public final HashtagRepository hashtagRepository;
    public final FeedHashtagRepository feedHashtagRepository;
    public final FeedRepository feedRepository;
    public final S3Uploader s3Uploader;

    @Override
    public String makeRandomS3Key() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void saveFeed(createFeedRequestDto requestDto) {
        Member findMember = memberRepository.findById(Long.valueOf(requestDto.uid)).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        String RandomS3Key = makeRandomS3Key();

//        s3Uploader.upload(requestDto.image, RandomS3Key);

        Company findCompany = companyRepository.findByvalue(requestDto.company).orElseThrow(()-> new IllegalArgumentException("회사가 존재하지 않습니다."));

        Feed saveFeed = feedRepository.save(new Feed(requestDto, RandomS3Key, findMember, findCompany));

        for (String hashtag : requestDto.hashtags) {
            Hashtag findHashtag = hashtagRepository.findByvalue(hashtag).orElseThrow(() -> new IllegalArgumentException("해시태그가 존재하지 않습니다."));
            feedHashtagRepository.save(new FeedHashtag(saveFeed, findHashtag));
        }
    }

}