package GDSC.FirstProject.service;

import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;

import java.util.List;

public interface FeedService {

    String makeRandomS3Key();

    String saveFeed(createFeedRequestDto requestDto);

    feedListResponseDto findFeedList(String company_name, Long people_count, List<String> hashtags, Long page_number);
}