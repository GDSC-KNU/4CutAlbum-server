package GDSC.FirstProject.dto.reponseDto;

import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.Member;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfileResponseDto {

    private String nickName;
    private String email;
    private List<createFeedResponseDto> feeds = new ArrayList<>();

    public ProfileResponseDto(Member member) {
        this.nickName = member.getNickName();
        this.email = email;
        for (Feed feed : member.getFeedList()) {
            this.feeds.add(new createFeedResponseDto(feed));
        }
    }
}
