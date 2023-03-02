package GDSC.FirstProject.dto.requsetDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileRequestDto {

    @NotEmpty
    private String uid;

}
