package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.QoriginalFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.originalFeedListDbDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import static GDSC.FirstProject.entity.QCompany.company;
import static GDSC.FirstProject.entity.QFeed.feed;
import static GDSC.FirstProject.entity.QFeedHashtag.feedHashtag;
import static GDSC.FirstProject.entity.QHashtag.hashtag;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedRepositoryImpl implements FeedRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    @Override
    public Slice<originalFeedListDbDto> findFeedList_Querydsl(String company_name, Long people_count, String[] hashtags, Pageable pageable) {
        QueryResults<originalFeedListDbDto> results = queryFactory
                .select(
                        new QoriginalFeedListDbDto(
                                feed.id,
                                feed.s3Key,
                                feed.peopleCount,
                                company.value,
                                hashtag.value))
                .from(feed)
                .join(feed.company, company)
                .join(feed.feedHashtagList, feedHashtag)
                .join(feedHashtag.hashtag, hashtag)
                .where(
                        company_nameEq(company_name),
                        people_countEq(people_count),
                        hashtagIn(hashtags))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(feed.createdDate.desc())
                .fetchResults();
        log.info("company_name: {}, people_count: {}, hashtags: {}", company_name, people_count, hashtags);
        return new SliceImpl<>(results.getResults(), pageable, results.getTotal() > pageable.getOffset() + pageable.getPageSize());
    }

    private BooleanExpression company_nameEq(String companyName) {
        return hasText(companyName) ? company.value.eq(companyName) : null;
    }

    private BooleanExpression people_countEq(Long peopleCount) {
        return peopleCount != null ? feed.peopleCount.eq(peopleCount) : null;
    }

    private BooleanExpression hashtagIn(String[] hashtags) {
        if (hashtags.length == 0) return null;
        return hashtag.value.in(hashtags);
    }
}
