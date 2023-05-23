package GDSC.FirstProject.dto.requsetDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateFeedRequestDto {
    @NotEmpty
    private String uid;

    private String[] hashtags;

    private Long peopleCount;

    private String company;

    private String comment;

}
