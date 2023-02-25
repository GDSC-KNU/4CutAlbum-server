package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.FeedHashtag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedHashtagRepository extends JpaRepository<FeedHashtag, Long> {

    FeedHashtag save(FeedHashtag feedHashtag);

    Optional<FeedHashtag[]> findAllByFeed(Feed feed);
}

