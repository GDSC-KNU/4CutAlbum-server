package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Hashtag;
import lombok.Builder;

@Builder
public class createFeedResponseDto {

    public String image;
    public Hashtag[] hashtags;
    public Integer peopleCount;
    public String company;
}
