package GDSC.FirstProject.entity;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "feed")
@Getter @Setter
public class Feed {

    @Id
    @GeneratedValue
    @Column(name = "feed_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Integer peopleCount;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    private LocalDateTime createdDate;

    private String s3Key;

    public Feed(createFeedRequestDto requestDto, String s3Key, Member member, Company company){
        this.likes = 0;
        this.peopleCount = requestDto.peopleCount;
        this.createdDate = LocalDateTime.now();
        this.s3Key = s3Key;
        this.member = member;
        this.company = company;
    }
}
