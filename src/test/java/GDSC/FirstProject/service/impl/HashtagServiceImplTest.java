package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.reponseDto.hashtagsDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HashtagServiceImplTest {

    @Autowired
    HashtagServiceImpl service;

    @Test
    public void findAllHashtagTest() throws Exception{
        //given

        //when
        hashtagsDto DBDTO = service.findAllHashtag();
        List<String> hashtags = DBDTO.getHashtags();

        //then
        assertThat(hashtags).isNotNull();
        for (String s : hashtags) {
            System.out.println(s);
        }
    }
}