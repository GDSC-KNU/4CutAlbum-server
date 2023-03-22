package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.repository.CompanyRepository;
import GDSC.FirstProject.repository.FeedHashtagRepository;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.repository.HashtagRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public void saveFeedTest() {
        //given
        createFeedRequestDto requestDto = createFeedRequestDto.builder()
                .uid("1")
                .image("image")
                .company("인생네컷")
                .hashtags(new String[]{"test1", "test2"})
                .peopleCount(2L)
                .comment("testComment1")
                .build();

        //when
//        feedController.saveFeed(requestDto);

        //then
//        Assertions.assertThat(findFeed.getId()).isEqualTo(saveFeed.getId());
    }

    @Test
    @DisplayName("피드컨트롤러 피드리스트 테스트")
    public void feedListTest(){
        //given
//        String company_name = "인생네컷";
        String company_name = null;
//        Long people_count = 2L;
        Long people_count = null;
        Long page_number = 0L;
        List<String> hashtags = null;
//        List<String> hashtags = new ArrayList<>();
//        hashtags.add("test1");
//        hashtags.add("test2");

        //when
        feedListResponseDto feedListResponseDto = feedController.feedList(company_name, people_count, hashtags, page_number);
        distinctFeedListDbDto[] feedList = feedListResponseDto.getFeedList();
        Long pageNumber = feedListResponseDto.getPage_number();
        boolean hasNext = feedListResponseDto.isHasNext();

        //then
        for (distinctFeedListDbDto distinctFeedListDbDto : feedList) {
            System.out.println("distinctFeedListDbDto.toString() = " + distinctFeedListDbDto.toString());
        }
        Assertions.assertThat(pageNumber).isEqualTo(0L);
        Assertions.assertThat(hasNext).isFalse();
    }

    @Test
    public void 피드_상세정보_컨트롤러_테스트() throws Exception{
        //given
        Long id = 1702L;

        //when
        feedInfoResponseDto feedInfoResponseDto = feedController.feedInfo(id);

        //then
        System.out.println("feedInfoResponseDto.toString() = " + feedInfoResponseDto.toString());
    }
}
