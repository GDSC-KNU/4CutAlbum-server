package GDSC.FirstProject.dto.requsetDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class feedListRequestDto {

    public String company;
    public Integer people_count;
    public String[] hashtag;
}