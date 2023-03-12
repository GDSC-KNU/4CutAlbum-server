package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.FeedHashtag;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfileFeedResponseDto {

    @NotEmpty
    private String image;

    private List<String> hashtags = new ArrayList<>();

    @NotEmpty
    private Long peopleCount;

    private String company;


    public ProfileFeedResponseDto(Feed feed) {

        for (FeedHashtag feedHashtag: feed.getFeedHashtagList()){
            this.hashtags.add(feedHashtag.getHashtag().getValue());
        }
        this.image = "https://necut-test.s3.ap-northeast-2.amazonaws.com/" + feed.getS3Key();
        this.peopleCount = feed.getPeopleCount();
        this.company = feed.getCompany().getValue();
    }
}
