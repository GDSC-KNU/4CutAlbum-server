package GDSC.FirstProject.converter;

import GDSC.FirstProject.dto.reponseDto.createFeedResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    FeedConverter feedConverter = new FeedConverter();

    @Test
    public void FeedConverterTest() throws Exception{
        //given
        createFeedRequestDto requestDto = createFeedRequestDto.builder()
                .uid("1")
                .image("image")
                .company("인생네컷")
                .hashtags(new String[]{"test1", "test2"})
                .peopleCount(2)
                .build();

        //when
        createFeedResponseDto responseDto = feedConverter.convert(requestDto);

        //then
        System.out.println("responseDto.toString() = " + responseDto.toString());
    }
}
