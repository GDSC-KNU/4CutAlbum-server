package GDSC.FirstProject.converter;

import GDSC.FirstProject.dto.dbDto.originalFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class originalFeedListDbDtoToDistinctFeedListDbDto implements Converter<originalFeedListDbDto[], distinctFeedListDbDto[]> {


    private String s3Url = "https://necut-test.s3.ap-northeast-2.amazonaws.com/";
    @Override
    public distinctFeedListDbDto[] convert(originalFeedListDbDto[] source) {

        Set hashTagSet = new HashSet();
        Set<distinctFeedListDbDto> responseDtos = new LinkedHashSet<>();

        for (originalFeedListDbDto DbDto : source)
            hashTagSet.add(DbDto.getHashtag());

        String[] hashTagList = (String[]) ((List) new ArrayList(hashTagSet))
                .toArray(new String[((List) new ArrayList(hashTagSet)).size()]);

        for (originalFeedListDbDto originalFeedListDbDto : source) {
            responseDtos.add(new distinctFeedListDbDto(
                    originalFeedListDbDto.getFeed_id(),
                    s3Url + originalFeedListDbDto.getImage(),
                    originalFeedListDbDto.getPeople_count(),
                    originalFeedListDbDto.getCompany_name(),
                    hashTagList));
        }

        return responseDtos.toArray(new distinctFeedListDbDto[responseDtos.size()]);
    }
}
