package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.service.FeedService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class FeedServiceImplTest {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedService feedService;

    @Test
    public void ServiceTest() throws Exception{
        //given
        String company_name = "인생네컷";
        Long people_count = 2L;
        List<String> hashtags = new ArrayList<>();
        hashtags.add("test1");
        hashtags.add("test2");
        Long page_number = 0L;

        //when
        feedListResponseDto feedList = feedService.findFeedList(company_name, people_count, hashtags, page_number);
        distinctFeedListDbDto[] list = feedList.getFeedList();
        Long pageNumber = feedList.getPage_number();
        boolean hasNext = feedList.isHasNext();

        //then
        for (distinctFeedListDbDto distinctFeedListDbDto : list) {
            System.out.println(distinctFeedListDbDto.toString());
        }
        assertThat(pageNumber).isEqualTo(0L);
        assertThat(hasNext).isTrue();
    }
}