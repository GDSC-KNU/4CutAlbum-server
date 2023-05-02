package GDSC.FirstProject.repository;

import GDSC.FirstProject.dto.dbDto.*;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;

import java.util.List;

import static GDSC.FirstProject.entity.QCompany.company;
import static GDSC.FirstProject.entity.QFeed.feed;
import static GDSC.FirstProject.entity.QFeedHashtag.feedHashtag;
import static GDSC.FirstProject.entity.QHashtag.hashtag;
import static org.springframework.util.StringUtils.hasText;

@Repository
@RequiredArgsConstructor
@Slf4j
public class FeedRepositoryImpl implements FeedRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<feedListDbDto> findFeedList_Querydsl(String company_name, Long people_count, String[] hashtags, Pageable pageable) {
        QueryResults<feedListDbDto> results = queryFactory
                .select(
                        new QfeedListDbDto(
                                feed.id,
                                feed.s3Key,
                                feed.peopleCount,
                                company.value))
                .distinct()
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
        return new SliceImpl<>(results.getResults(), pageable, results.getTotal() > pageable.getOffset() + pageable.getPageSize());
    }

    @Override
    public List<PartOfFeedListDbDto> findPartOfFeedList_Querydsl(String company_name, Long people_count, String[] hashtags) {
        QueryResults<PartOfFeedListDbDto> results = queryFactory
                .select(
                        new QPartOfFeedListDbDto(
                                feed.id,
                                hashtag.value))

                .from(feed)
                .join(feed.company, company)
                .join(feed.feedHashtagList, feedHashtag)
                .join(feedHashtag.hashtag, hashtag)
                .where(
                        company_nameEq(company_name),
                        people_countEq(people_count),
                        hashtagIn(hashtags))
                .orderBy(feed.createdDate.desc())
                .fetchResults();

        return results.getResults();

    }


    private BooleanExpression company_nameEq(String companyName) {
        return hasText(companyName) ? company.value.eq(companyName) : null;
    }

    private BooleanExpression people_countEq(Long peopleCount) {
        return peopleCount != 0 ? feed.peopleCount.eq(peopleCount) : null;
    }

    private BooleanExpression hashtagIn(String[] hashtags) {
        return (hashtags.length == 0 || hashtags == null) ? null : hashtag.value.in(hashtags);
    }
}
