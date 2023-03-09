package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.feedListDbDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class FeedRepositoryTest {

    @Autowired
    FeedRepository feedRepository;

    @Test
    public void findFeedListTest() throws Exception {
        //given
        String company_name = "인생네컷";
        Long people_count = 2L;
        String[] hashtags = new String[]{"test1", "test2"};

        //when
        List<feedListDbDto> feedList = feedRepository.findFeedList(company_name, people_count, hashtags)
                .orElseThrow(() -> new Exception("feedList is null"));

        //then
        Assertions.assertThat(feedList).isNotNull();
        for (GDSC.FirstProject.dto.dbDto.feedListDbDto feedListDbDto : feedList) {
            System.out.println("feedListDbDto = " + feedListDbDto.toString());
        }
    }
}
