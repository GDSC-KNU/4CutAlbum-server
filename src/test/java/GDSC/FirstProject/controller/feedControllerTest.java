package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.entity.Company;
import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.FeedHashtag;
import GDSC.FirstProject.repository.CompanyRepository;
import GDSC.FirstProject.repository.FeedHashtagRepository;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.repository.HashtagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class feedControllerTest {

    @Autowired
    feedController feedController;
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    FeedHashtagRepository feedHashtagRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void saveFeedTest() throws Exception {
        //given
        createFeedRequestDto requestDto = createFeedRequestDto.builder()
                .uid("1")
                .image("image")
                .company("인생네컷")
                .hashtags(new String[]{"test1", "test2"})
                .peopleCount(2)
                .build();

        //when
        Feed saveFeed = feedController.saveFeed(requestDto);

        Company findCompany = companyRepository.findByvalue("인생네컷")
                .orElseThrow(() -> new RuntimeException());

        boolean TF = hashtagRepository.existsAllByvalueIn(requestDto.hashtags);

        Feed findFeed = feedRepository.findById(saveFeed.getId()).
                orElseThrow(() -> new RuntimeException());

        FeedHashtag[] findFeedHashtags = feedHashtagRepository.findAllByFeed(findFeed)
                .orElseThrow(() -> new RuntimeException());

        //then
        assertThat(findFeedHashtags.length).isEqualTo(2);
        assertThat(TF).isEqualTo(true);
        assertThat(findFeed.getId()).isEqualTo(saveFeed.getId());

    }
}
