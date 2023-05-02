package GDSC.FirstProject.service;

import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;

import java.util.List;

public interface FeedService {

    String makeRandomS3Key();

    String concatRandomS3keyAndExtension(String FileNameExtension);

    String saveFeed(createFeedRequestDto requestDto);

    feedInfoResponseDto findFeedInfo(Long id);

    void deleteFeedById(Long id);

    Long translatePeopleCount(String people_count);

    feedListResponseDto makeFeedListResponseDto(String company_name, Long people_count, List<String> hashtags, Long page_number);

}