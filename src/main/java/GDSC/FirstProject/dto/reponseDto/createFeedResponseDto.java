package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Company;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class createFeedResponseDto {

    @NotEmpty
    private String image;

    private String[] hashtags;

    @NotEmpty
    private Integer peopleCount;

    private Company company;
}
