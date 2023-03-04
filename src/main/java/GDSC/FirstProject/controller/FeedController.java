package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public void saveFeed(@RequestBody createFeedRequestDto requestDto){
        feedService.saveFeed(requestDto);
    }
}