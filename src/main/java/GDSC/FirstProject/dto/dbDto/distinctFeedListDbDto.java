package GDSC.FirstProject.dto.dbDto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class distinctFeedListDbDto {

    public Long feed_id;
    public String image;
    public Long people_count;
    public String company_name;
    public String[] hashtag;
    public distinctFeedListDbDto(Long feed_id, String image, Long people_count, String company_name, String[] hashtag) {
        this.feed_id = feed_id;
        this.image = image;
        this.people_count = people_count;
        this.company_name = company_name;
        this.hashtag = hashtag;
    }
}