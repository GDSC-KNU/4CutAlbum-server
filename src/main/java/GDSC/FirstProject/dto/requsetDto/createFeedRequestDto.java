package GDSC.FirstProject.dto.requsetDto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class createFeedRequestDto {

    @NotEmpty
    public String uid;

    @NotEmpty
    public String image;

    @NotEmpty
    public String image_name;

    public String[] hashtags;

    @NotEmpty @Min(1)
    public Long peopleCount;

    public String company;

    @NotNull
    public String comment;


    public createFeedRequestDto() {
    }
}
