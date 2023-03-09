package GDSC.FirstProject.converter;

import GDSC.FirstProject.repository.FeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;

@SpringBootTest
class converterTest {

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    ConversionService conversionService;

//    @Test
//    public void feedListConverterTest() throws Exception{
//        //given
//        List<feedListDbDto> feedListDbDtos = feedRepository.findFeedList("인생네컷", 2L, new String[]{"test1", "test2"})
//                .orElseThrow(() -> new Exception("feedList is null"));
//
//        //when
//        feedListResponseDto[] responseDtos = conversionService.convert(feedListDbDtos.toArray(new feedListDbDto[feedListDbDtos.size()]), feedListResponseDto[].class);
//
//        //then
//        for (feedListResponseDto responseDto : responseDtos) {
//            System.out.println("responseDto = " + responseDto.toString());
//        }
//
//    }
}