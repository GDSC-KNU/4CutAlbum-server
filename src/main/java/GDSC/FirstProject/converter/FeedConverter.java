package GDSC.FirstProject.converter;

import GDSC.FirstProject.dto.reponseDto.createFeedResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FeedConverter implements Converter<createFeedRequestDto, createFeedResponseDto> {

    @Override
    public createFeedResponseDto convert(createFeedRequestDto source) {
        return createFeedResponseDto.builder()
                .image(source.getImage())
                .hashtags(source.getHashtags())
                .peopleCount(source.getPeopleCount())
                .company(source.getCompany())
                .build();

    }
}
