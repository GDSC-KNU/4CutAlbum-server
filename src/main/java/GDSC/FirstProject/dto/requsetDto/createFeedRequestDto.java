package GDSC.FirstProject.dto.requsetDto;

import GDSC.FirstProject.entity.Hashtag;
import lombok.Getter;

@Getter
public class createFeedRequestDto {

    public String uid;
    public String image;
    public Hashtag[] hashtags;
    public Integer peopleCount;
    public String company;
}
