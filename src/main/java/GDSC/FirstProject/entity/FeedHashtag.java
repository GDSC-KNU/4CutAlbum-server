package GDSC.FirstProject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "feed_hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FeedHashtag {

    @Id
    @GeneratedValue
    @Column(name = "feed_hashtag_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private Hashtag hashtag;
}
