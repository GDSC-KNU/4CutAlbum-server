package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.repository.CompanyRepository;
import GDSC.FirstProject.repository.FeedHashtagRepository;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.repository.HashtagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class feedControllerTest {

    @Autowired
    FeedController feedController;
    @Autowired
    FeedRepository feedRepository;
    @Autowired
    FeedHashtagRepository feedHashtagRepository;
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void saveFeedTest() {
        //given
        createFeedRequestDto requestDto = createFeedRequestDto.builder()
                .uid("1")
                .image("image")
                .company("인생네컷")
                .hashtags(new String[]{"test1", "test2"})
                .peopleCount(2)
                .build();

        //when
        feedController.saveFeed(requestDto);

        //then


    }
}
