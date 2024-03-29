package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.PartOfFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedListDbDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeedRepositoryCustom {

    Slice<feedListDbDto> findFeedList_Querydsl(
            @Param("company_name") String company_name,
            @Param("people_count") Long people_count,
            @Param("hashtags") String[] hashtags,
            Pageable pageable
    );

    List<PartOfFeedListDbDto> findPartOfFeedList_Querydsl(
            @Param("company_name") String company_name,
            @Param("people_count") Long people_count,
            @Param("hashtags") String[] hashtags
    );
}
