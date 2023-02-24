package GDSC.FirstProject.dto.requsetDto;

import lombok.Getter;

@Getter
public class createFeedRequestDto {

    public String uid;
    public String image;
    public String[] hashtags;
    public Integer peopleCount;
    public String company;
}
