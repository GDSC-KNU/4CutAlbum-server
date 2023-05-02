package GDSC.FirstProject.dto.dbDto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PartOfFeedListDbDto {

    public Long feed_id;
    public String hashtag;

    @QueryProjection
    public PartOfFeedListDbDto(Long feed_id, String hashtag) {
        this.feed_id = feed_id;
        this.hashtag = hashtag;
    }
}
