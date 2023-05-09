package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.PartOfFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.dbDto.feedListDbDto;
import GDSC.FirstProject.entity.Feed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FeedRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedRepositoryImpl feedRepositoryImpl;

    @Test
    public void 피드_상세정보_조회() throws Exception {
        //given
        Long feed_id = 1902L;

        //when
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(feed_id)
                .orElseThrow(() -> new Exception("피드가 존재하지 않습니다."));

        //then
        for (feedInfoDbDto feedInfoDbDto : feedInfoDbDtos) {
            System.out.println(feedInfoDbDto.toString());
        }
    }
    @Test
    public void findFeedList_QuerydslFixedTest() throws Exception{
        //given
        String company_name = "인생네컷";
        Long people_count = 0L;
        String[] hashtags = new String[]{};
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));

        //when
        Slice<feedListDbDto> slice = feedRepository.findFeedList_Querydsl(company_name, people_count, hashtags, pageRequest);
        List<feedListDbDto> content = slice.getContent();
        boolean hasNext = slice.hasNext();

        //then
        assertThat(hasNext).isFalse();
        for (feedListDbDto feedListDbDto : content) {
            System.out.println("feedListDbDto = " + feedListDbDto);
        }
    }

    @Test
    public void findPartOfFeedList_QuerydslTest() throws Exception{
        //given
        String company_name = "인생네컷";
        Long people_count = 0L;
        String[] hashtags = new String[]{};

        //when
        List<PartOfFeedListDbDto> result = feedRepository.findPartOfFeedList_Querydsl(company_name, people_count, hashtags);

        //then
        for (PartOfFeedListDbDto partOfFeedListDbDto : result) {
            System.out.println("partOfFeedListDbDto = " + partOfFeedListDbDto);
        }

    }

    @Test
    public void deleteFeedTest() throws Exception{
        //given

        //when
        feedRepository.deleteFeedById(2156L);

        //then
        Feed findFeed = feedRepository.findById(2156L).get();
        assertThat(findFeed).isNull();

    }
}
