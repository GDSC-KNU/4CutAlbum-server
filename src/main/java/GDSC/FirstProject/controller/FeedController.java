package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.*;
import GDSC.FirstProject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    public final MemberRepository memberRepository;
    public final CompanyRepository companyRepository;
    public final HashtagRepository hashtagRepository;
    public final FeedHashtagRepository feedHashtagRepository;
    public final FeedRepository feedRepository;
    @PostMapping
    public void saveFeed(@RequestBody createFeedRequestDto requestDto){

        Member findMember = memberRepository.findById(Long.valueOf(requestDto.uid)).orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));

        String s3Key = "s3Key";
        //

        Company findCompany = companyRepository.findByvalue(requestDto.company).orElseThrow(()-> new IllegalArgumentException("회사가 존재하지 않습니다."));

        Feed saveFeed = feedRepository.save(new Feed(requestDto, s3Key, findMember, findCompany));

        for (String hashtag : requestDto.hashtags) {
            Hashtag findHashtag = hashtagRepository.findByvalue(hashtag).orElseThrow(() -> new IllegalArgumentException("해시태그가 존재하지 않습니다."));
            feedHashtagRepository.save(new FeedHashtag(saveFeed, findHashtag));
        }
    }
}