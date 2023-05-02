package GDSC.FirstProject.dto.dbDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class feedListDbDto {

    public Long feed_id;
    public String image;
    public Long people_count;
    public String company_name;

    @QueryProjection
    public feedListDbDto(Long feed_id, String image, Long people_count, String company_name) {
        this.feed_id = feed_id;
        this.image = image;
        this.people_count = people_count;
        this.company_name = company_name;
    }
}
