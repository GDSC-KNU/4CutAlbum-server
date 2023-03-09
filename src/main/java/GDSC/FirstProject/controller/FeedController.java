package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public Map<String, String> saveFeed(@RequestBody createFeedRequestDto requestDto){
        Map<String, String> response = new HashMap<>();
        String url = feedService.saveFeed(requestDto);
        response.put("url", url);
        return response;
    }
    @GetMapping("/list/{company}/{people-count}/{hashtags}/{page-number}")
    public feedListResponseDto feedList(
            @PathVariable("company") String company,
            @PathVariable("people-count")Long people_count,
            @PathVariable("hashtags") List<String> hashtags,
            @PathVariable("page-number") Long page_number) {

        return feedService.findFeedList(company, people_count, hashtags, page_number);
    }
}