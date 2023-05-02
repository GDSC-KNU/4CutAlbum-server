package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.PartOfFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedListDbDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

public interface FeedRepositoryCustom {

    Slice<feedListDbDto> findFeedList_QuerydslFixed(
            @Param("company_name") String company_name,
            @Param("people_count") Long people_count,
            @Param("hashtags") String[] hashtags,
            Pageable pageable
    );

    Slice<PartOfFeedListDbDto> findPartOfFeedList_Querydsl(
            @Param("company_name") String company_name,
            @Param("people_count") Long people_count,
            @Param("hashtags") String[] hashtags,
            Pageable pageable
    );
}
