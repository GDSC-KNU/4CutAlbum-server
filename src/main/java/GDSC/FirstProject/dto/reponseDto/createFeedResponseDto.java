package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Company;
import lombok.Builder;

@Builder
public class createFeedResponseDto {

    public String image;
    public String[] hashtags;
    public Integer peopleCount;
    public Company company;
}
