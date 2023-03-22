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
//        String company_name = "인생네컷";
        String company_name = null;
//        Long people_count = 2L;
        Long people_count = null;
        List<String> hashtags = null;
//        List<String> hashtags = new ArrayList<>();
//        hashtags.add("test1");
//        hashtags.add("test2");
        Long page_number = 0L;

        //when
        feedListResponseDto feedList = feedService.findFeedList_Querydsl(company_name, people_count, hashtags, page_number);
        distinctFeedListDbDto[] list = feedList.getFeedList();
        Long pageNumber = feedList.getPage_number();
        boolean hasNext = feedList.isHasNext();

        //then
        for (distinctFeedListDbDto distinctFeedListDbDto : list) {
            System.out.println(distinctFeedListDbDto.toString());
        }
//        assertThat(pageNumber).isEqualTo(0L);
//        assertThat(hasNext).isTrue();
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
}