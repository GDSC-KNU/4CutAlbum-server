package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.*;
import GDSC.FirstProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/feeds")
@RequiredArgsConstructor
public class feedController {

    public final MemberRepository memberRepository;
    public final CompanyRepository companyRepository;
    public final HashtagRepository hashtagRepository;
    public final FeedHashtagRepository feedHashtagRepository;
    public final FeedRepository feedRepository;

    @PostMapping("/")
    public Feed saveFeed(createFeedRequestDto requestDto){

        Member findMember = memberRepository.findById(Long.valueOf(requestDto.uid))
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        //Todo: s3에 이미지 저장하는 로직 추가해야 함
        String s3Key = "s3Key";
        //

        Company findCompany = companyRepository.findByvalue(requestDto.company)
                .orElseThrow(()-> new IllegalArgumentException("회사가 존재하지 않습니다."));

        Feed feed = new Feed(requestDto, s3Key, findMember, findCompany);
        Feed saveFeed = feedRepository.save(feed);

        //Todo: 피드-해시태그 저장하는 로직
        String[] hashtags = requestDto.hashtags;
        for (String hashtag : hashtags) {
            Hashtag findHashtag = hashtagRepository.findByvalue(hashtag).
                    orElseThrow(() -> new IllegalArgumentException("해시태그가 존재하지 않습니다."));

            feedHashtagRepository.save(new FeedHashtag(saveFeed, findHashtag));
        }

        return saveFeed;
    }
}
