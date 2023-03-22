package GDSC.FirstProject.service;

import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;

import java.util.List;

public interface FeedService {

    String makeRandomS3Key();

    String concatRandomS3keyAndExtension(String FileNameExtension);

    String saveFeed(createFeedRequestDto requestDto);

    feedListResponseDto findFeedList_Querydsl(String company_name, Long people_count, List<String> hashtags, Long page_number);


    feedInfoResponseDto findFeedInfo(Long id);

    void deleteFeedById(Long id);
}