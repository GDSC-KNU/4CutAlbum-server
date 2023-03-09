package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class feedListResponseDto {

    distinctFeedListDbDto[] feedList;
    Long page_number;
    boolean hasNext;

}
