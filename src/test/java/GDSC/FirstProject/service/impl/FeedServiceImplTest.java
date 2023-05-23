package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.dbDto.distinctFeedListDbDto;
import GDSC.FirstProject.dto.dbDto.feedInfoDbDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.UpdateFeedRequestDto;
import GDSC.FirstProject.entity.Company;
import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.FeedHashtag;
import GDSC.FirstProject.entity.Hashtag;
import GDSC.FirstProject.repository.CompanyRepository;
import GDSC.FirstProject.repository.FeedHashtagRepository;
import GDSC.FirstProject.repository.FeedRepository;
import GDSC.FirstProject.repository.HashtagRepository;
import GDSC.FirstProject.service.FeedService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.convert.ConversionService;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FeedServiceImplTest {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    FeedRepository feedRepository;

    @Autowired
    FeedHashtagRepository feedHashtagRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    HashtagRepository hashtagRepository;

    @Autowired
    FeedService feedService;

    @Test
    public void ServiceTest() throws Exception {
        //given
        String company_name = "";
        Long people_count = 0L;
        List<String> hashtags = new ArrayList<>();
        Long page_number = 0L;

        //when
        feedListResponseDto feedList = feedService.makeFeedListResponseDto(company_name, people_count, hashtags, page_number);
        distinctFeedListDbDto[] list = feedList.getFeedList();

        //then
        for (distinctFeedListDbDto distinctFeedListDbDto : list) {
            System.out.println(distinctFeedListDbDto.toString());
        }
    }

    @Test
    public void 피드_상세정보_조회_테스트() throws Exception {
        //given
        Long id = 1702L;

        //when
        feedInfoResponseDto feedInfo = feedService.findFeedInfo(id);

        //then
        System.out.println(feedInfo.toString());
    }

    @Test
    public void 피드삭제_메서드_테스트() throws Exception {
        //given
        Long id = 1902L;

        //when
        feedRepository.deleteFeedById(id);
        List<feedInfoDbDto> feedInfoDbDtos = feedRepository.findFeedInfo(id).get();

        //then
        Assertions.assertThat(feedInfoDbDtos.size()).isEqualTo(0);
    }


    @Test
    @Transactional
    public void 피드_수정_테스트() throws Exception {
        //given
        String[] hashtags = {};
        UpdateFeedRequestDto dto = new UpdateFeedRequestDto("2252", hashtags, 0L, "", "");

        Feed initialFeed = feedRepository.findById(Long.valueOf(dto.getUid()))
                .orElseThrow(() -> new IllegalAccessError("피드가 존재하지 않습니다."));

        //when
        Long id = feedService.updateFeed(dto);

        //then
        Company company = null;
        Feed feed = feedRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("피드가 존재하지 않습니다."));
        if (!dto.getCompany().equals("")) {
            company = companyRepository.findByvalue(dto.getCompany())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
        }

        Set<Hashtag> expectedHashSet = new HashSet<Hashtag>();
        for (String hashtagValue: hashtags) {
            Hashtag hashtag = hashtagRepository.findByvalue(hashtagValue)
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 해시태그입니다."));
            expectedHashSet.add(hashtag);
        }
        Set<Hashtag> hashtagSet = new HashSet<Hashtag>();
        for (FeedHashtag feedHashtag: feed.getFeedHashtagList()){
            System.out.println("hashtag: " + feedHashtag.getHashtag().getValue());
            hashtagSet.add(feedHashtag.getHashtag());
        }

        System.out.println("comment: " + feed.getComment());
        System.out.println("peopleCount: " + feed.getPeopleCount());
        System.out.println("company: " + feed.getCompany().getValue());
        System.out.println("hashtag: " + feed.getFeedHashtagList());
        if (dto.getPeopleCount() != 0L) {
            assertEquals(dto.getPeopleCount(), initialFeed.getPeopleCount());
        } else {
            assertEquals(initialFeed.getPeopleCount(), feed.getPeopleCount());
        }

        if (!dto.getComment().equals("")) {
            assertEquals(dto.getComment(), feed.getComment());
        } else {
            assertEquals(initialFeed.getComment(), feed.getComment());
        }

        if (!dto.getCompany().equals("")) {
            assertEquals(company, feed.getCompany());
        } else {
            assertEquals(initialFeed.getCompany(), feed.getCompany());
        }

        if (dto.getHashtags().length != 0) {
            assertEquals(expectedHashSet.size(), hashtagSet.size());
            for (Hashtag hashtag: expectedHashSet) {
                assertTrue(hashtagSet.contains(hashtag));
            }
        } else {
            assertEquals(initialFeed.getFeedHashtagList().size(), hashtagSet.size());
        }

    }

    @Test
    public void makeFeedListResponseDtoTest() throws Exception {
        //given
        String company_name = "";
        Long people_count = 0L;
        List<String> hashtags = new ArrayList<String>();
        hashtags.add("test1");
        hashtags.add("test2");

        //when

        //then
        feedListResponseDto result = feedService.makeFeedListResponseDto(company_name, people_count, hashtags, 0L);
        distinctFeedListDbDto[] feedList = result.getFeedList();
        for (distinctFeedListDbDto distinctFeedListDbDto : feedList) {
            System.out.println(distinctFeedListDbDto.toString());
        }
    }
}