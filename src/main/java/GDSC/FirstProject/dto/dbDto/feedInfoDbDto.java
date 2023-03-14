package GDSC.FirstProject.dto.dbDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class feedInfoDbDto {
    public Long id;
    public String nick_name;
    public String s3_key;
    public String company_name;
    public Long likes;
    public String comment;
    public String hashtags;
    public Long people_count;
}