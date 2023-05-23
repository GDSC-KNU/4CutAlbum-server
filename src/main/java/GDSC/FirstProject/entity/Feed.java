package GDSC.FirstProject.entity;

import GDSC.FirstProject.dto.requsetDto.UpdateFeedRequestDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "feed")
@Getter @Setter
@EqualsAndHashCode
public class Feed {

    @Id
    @GeneratedValue
    @Column(name = "feed_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long peopleCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "feed", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedHashtag> feedHashtagList;

    private LocalDateTime createdDate;

    private String s3Key;

    private Long likes;

    private String comment;

    public Feed(createFeedRequestDto requestDto, String s3Key, Member member, Company company){
        this.likes = 0L;
        this.peopleCount = requestDto.peopleCount;
        this.createdDate = LocalDateTime.now();
        this.s3Key = s3Key;
        this.member = member;
        this.company = company;
        this.comment = requestDto.comment;
    }

    public void update(UpdateFeedRequestDto dto){
        if (dto.getPeopleCount() != 0) this.peopleCount = dto.getPeopleCount();
        if (!dto.getComment().equals("")) this.comment = dto.getComment();
    }

    public void updateCompany(Company company){
        this.company = company;
    }

    public void updateHashtag(List<Hashtag> hashtagList) {
        this.feedHashtagList.clear();
        for (Hashtag hashtag: hashtagList) {
            this.feedHashtagList.add(new FeedHashtag(this, hashtag));
        }
    }
}
