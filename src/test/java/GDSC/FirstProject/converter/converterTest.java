package GDSC.FirstProject.converter;

import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.repository.FeedRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

import java.util.List;

@SpringBootTest
class converterTest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    ConversionService conversionService;

    @Test
    public void 피드_상세정보_컨버터_테스트() throws Exception {
        //given
        Long feed_id = 1702L;
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(feed_id)
                .orElseThrow(() -> new Exception("피드가 존재하지 않습니다."));

        //when
        feedInfoResponseDto convert = conversionService.convert(feedInfoDbDtos, feedInfoResponseDto.class);

        //then
        System.out.println("convert.toString() = " + convert.toString());
    }
}