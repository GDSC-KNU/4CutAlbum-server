package GDSC.FirstProject.converter;

import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class feedInfoDbDtoTofeedInfoResponseDto implements Converter<List<feedInfoDbDto>, feedInfoResponseDto> {

    @Override
    public feedInfoResponseDto convert(List<feedInfoDbDto> sourceList) {

        feedInfoDbDto[] source = sourceList.toArray(new feedInfoDbDto[sourceList.size()]);
        feedInfoResponseDto responseDto;
        Set hashTagSet = new HashSet();

        for (feedInfoDbDto DbDto : source)
            hashTagSet.add(DbDto.getHashtags());

        String[] hashTagList = (String[]) ((List) new ArrayList(hashTagSet))
                .toArray(new String[((List) new ArrayList(hashTagSet)).size()]);

        responseDto = feedInfoResponseDto.builder()
                .id(source[0].getId())
                .nick_name(source[0].getNick_name())
                .s3_key(source[0].getS3_key())
                .company_name(source[0].getCompany_name())
                .likes(source[0].getLikes())
                .comment(source[0].getComment())
                .hashtags(hashTagList)
                .people_count(source[0].getPeople_count())
                .build();

        return responseDto;
    }
}
