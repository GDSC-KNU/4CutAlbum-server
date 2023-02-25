package GDSC.FirstProject.dto.requsetDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter @ToString
public class createFeedRequestDto {

    @NotEmpty
    public String uid;

    @NotEmpty
    public String image;

    public String[] hashtags;

    @NotEmpty @Min(1)
    public Integer peopleCount;

    public String company;
}
