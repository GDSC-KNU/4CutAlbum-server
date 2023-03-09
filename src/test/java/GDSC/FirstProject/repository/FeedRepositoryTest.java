package GDSC.FirstProject.repository;

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

    @Test
    @DisplayName("피드리스트 조회 테스트")
    public void findFeedListTest() throws Exception {
        //given
        String company_name = "인생네컷";
        Long people_count = 2L;
        String[] hashtags = new String[]{"test1", "test2"};
        PageRequest pageRequest = PageRequest.of(1, 5, Sort.by(Sort.Direction.DESC, "createdDate"));

        //when
        Slice<originalFeedListDbDto> slice = feedRepository.findFeedList(company_name, people_count, hashtags, pageRequest);
        List<originalFeedListDbDto> dtos = slice.getContent();
        boolean hasNext = slice.hasNext();

        //then
        Assertions.assertThat(dtos.size()).isEqualTo(5);
        Assertions.assertThat(hasNext).isTrue();

        System.out.println(dtos.getClass());
        System.out.println(slice.getNumber());
        for (originalFeedListDbDto dto : dtos) {
            System.out.println(dto.toString());
        }


    }
}
