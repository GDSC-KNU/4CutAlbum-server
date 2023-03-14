package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.dbDto.originalFeedListDbDto;
import GDSC.FirstProject.entity.Company;
import GDSC.FirstProject.entity.Feed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {

    Optional<Feed> findBycompany(Company company);

    Optional<Feed> findById(Long id);

    @Query(
            " select new GDSC.FirstProject.dto.dbDto.originalFeedListDbDto(f.id, f.s3Key, f.peopleCount, c.value, h.value)" +
                    " from Feed f" +
                    "          inner join Company c on f.company = c" +
                    "          inner join FeedHashtag fh on f = fh.feed" +
                    "          inner join Hashtag h on fh.hashtag = h" +
                    " where c.value = :company_name and f.peopleCount = :people_count and h.value IN :hashtags" +
                    " order by f.createdDate desc"
    )
    Slice<originalFeedListDbDto> findFeedList(
            @Param("company_name") String company_name,
            @Param("people_count") Long people_count,
            @Param("hashtags") String[] hashtags,
            Pageable pageable
    );

    @Query(" select new GDSC.FirstProject.dto.dbDto.feedInfoDbDto( f.id, m.nickName, f.s3Key, c.value, f.likes, f.comment, h.value, f.peopleCount) " +
            " from Feed f" +
            "          inner join Company c on f.company = c" +
            "          inner join FeedHashtag fh on f = fh.feed" +
            "          inner join Hashtag h on fh.hashtag = h" +
            "          inner join Member m on f.member = m" +
            " where f.id = :id"
    )
    Optional<List<feedInfoDbDto>> findFeedInfo(@Param("id") Long id);
}