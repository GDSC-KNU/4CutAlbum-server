package GDSC.FirstProject.dto.reponseDto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class feedInfoResponseDto {

    public Long id;
    public String nick_name;
    public String s3_key;
    public String company_name;
    public Long likes;
    public String comment;
    public String[] hashtags;
    public Long people_count;
}
