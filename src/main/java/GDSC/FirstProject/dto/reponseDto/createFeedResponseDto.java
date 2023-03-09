package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Feed;
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
    private Long peopleCount;

    private String company;

    public createFeedResponseDto() {
    }

    public createFeedResponseDto(Feed feed) {
//        this.image =
//        this.hashtags =
        this.peopleCount = feed.getPeopleCount();
        this.company = feed.getCompany().getValue();

    }
}
