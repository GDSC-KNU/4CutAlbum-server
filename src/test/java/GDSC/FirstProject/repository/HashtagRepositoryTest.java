package GDSC.FirstProject.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HashtagRepositoryTest {

    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void existsByvalueTest() throws Exception{
        //given
        String values[] = {"test", "test2"};

        //when
        boolean TF = hashtagRepository.existsAllByvalueIn(values);

        //then
        Assertions.assertThat(TF).isTrue();
    }
}