package GDSC.FirstProject.service;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;

public interface FeedService {

    String makeRandomS3Key();

    void saveFeed(createFeedRequestDto requestDto);
}