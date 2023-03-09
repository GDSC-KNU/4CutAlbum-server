package GDSC.FirstProject.dto.requsetDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class feedListRequestDto {

    public String company;
    public Long people_count;
    public List<String> hashtag;
}