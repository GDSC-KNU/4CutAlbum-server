package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.service.FeedService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class FeedServiceImplTest {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    FeedRepository feedRepository;


    @Autowired
    FeedService feedService;

    @Test
    public void ServiceTest() throws Exception {
        //given
        String company_name = "";
        Long people_count = 0L;
        List<String> hashtags = new ArrayList<>();
        Long page_number = 0L;

        //when
        feedListResponseDto feedList = feedService.makeFeedListResponseDto(company_name, people_count, hashtags, page_number);
        distinctFeedListDbDto[] list = feedList.getFeedList();

        //then
        for (distinctFeedListDbDto distinctFeedListDbDto : list) {
            System.out.println(distinctFeedListDbDto.toString());
        }
    }

    @Test
    public void 피드_상세정보_조회_테스트() throws Exception {
        //given
        Long id = 1702L;

        //when
        feedInfoResponseDto feedInfo = feedService.findFeedInfo(id);

        //then
        System.out.println(feedInfo.toString());
    }

    @Test
    public void 피드삭제_메서드_테스트() throws Exception {
        //given
        Long id = 1902L;

        //when
        feedRepository.deleteFeedById(id);
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(id).get();

        //then
        Assertions.assertThat(feedInfoDbDtos.size()).isEqualTo(0);
    }

    @Test
    public void makeFeedListResponseDtoTest() throws Exception {
        //given
        String company_name = "";
        Long people_count = 0L;
        List<String> hashtags = new ArrayList<String>();
        hashtags.add("test1");
        hashtags.add("test2");

        //when

        //then
        feedListResponseDto result = feedService.makeFeedListResponseDto(company_name, people_count, hashtags, 0L);
        distinctFeedListDbDto[] feedList = result.getFeedList();
        for (distinctFeedListDbDto distinctFeedListDbDto : feedList) {
            System.out.println(distinctFeedListDbDto.toString());
        }
    }
}