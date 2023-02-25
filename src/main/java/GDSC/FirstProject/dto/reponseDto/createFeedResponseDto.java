package GDSC.FirstProject.dto.reponseDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@ToString
public class createFeedResponseDto {

    @NotEmpty
    private String image;

    private String[] hashtags;

    @NotEmpty
    private Integer peopleCount;

    private String company;

    public createFeedResponseDto() {
    }
}
