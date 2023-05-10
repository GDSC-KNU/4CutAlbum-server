package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Hashtag;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HashtagRepositoryTest {

    @Autowired
    HashtagRepository hashtagRepository;

    @Test
    public void existsByvalueTest() throws Exception {
        //given
        String values[] = {"test", "test2"};

        //when
        boolean TF = hashtagRepository.existsAllByvalueIn(values);

        //then
        Assertions.assertThat(TF).isTrue();
    }

    @Test
    public void saveHashtag() throws Exception {
        //given
        List<Hashtag> hashtags = new ArrayList<>();
        hashtags.add(new Hashtag("졸업"));
        hashtags.add(new Hashtag("생일"));
        hashtags.add(new Hashtag("커플"));
        hashtags.add(new Hashtag("우정샷"));

        //when
        hashtagRepository.saveAll(hashtags);

        //then
        List<Hashtag> findHashtags = hashtagRepository.findAll();
        for (Hashtag findHashtag : findHashtags) {
            System.out.println(findHashtag.getValue());
        }
    }

    @Test
    public void findAllHashtag() throws Exception{
        //given

        //when
        List<Hashtag> result = hashtagRepository.findAll();

        //then
        for(Hashtag hashtag : result){
            System.out.println(hashtag.getValue());
        }
    }
}