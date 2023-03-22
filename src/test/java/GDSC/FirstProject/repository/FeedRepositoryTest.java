package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.dbDto.originalFeedListDbDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;

import java.util.List;

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
    @DisplayName("Querydsl 피드리스트 조회 테스트")
    public void findFeedList_QuerydslTest() throws Exception {
        //given
        String company_name = "인생네컷";
        Long people_count = 2L;
        String[] hashtags = new String[]{"test1", "test2"};
//        String[] hashtags = new String[]{"test1"};
//        String[] hashtags = null;
        PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "createdDate"));

        //when
        Slice<originalFeedListDbDto> slice = feedRepositoryImpl.findFeedList_Querydsl(company_name, people_count, hashtags, pageRequest);
        List<originalFeedListDbDto> dtos = slice.getContent();
        boolean hasNext = slice.hasNext();

        //then
//        Assertions.assertThat(dtos.size()).isEqualTo(3);
        Assertions.assertThat(hasNext).isFalse();

        for (originalFeedListDbDto dto : dtos) {
            System.out.println("dto = " + dto);
        }
    }

}
