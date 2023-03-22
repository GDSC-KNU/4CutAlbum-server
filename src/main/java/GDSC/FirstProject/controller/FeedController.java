package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.service.FeedService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Validated
public class FeedController {

    private final FeedService feedService;

    @PostMapping
    public Map<String, String> saveFeed(@RequestBody createFeedRequestDto requestDto){
        Map<String, String> response = new HashMap<>();
        String url = feedService.saveFeed(requestDto);
        response.put("url", url);
        return response;
    }
    @GetMapping("/list")
    public feedListResponseDto feedList(
            @RequestParam(name = "company", required = false) String company,
            @Min(1) @RequestParam(name = "people-count", required = false) Long people_count,
            @RequestParam(name = "hashtags", required = false) List<String> hashtags,
            @RequestParam("page-number") @NotNull Long page_number) {

        return feedService.findFeedList_Querydsl(company, people_count, hashtags, page_number);
    }

    @GetMapping("/")
    public feedInfoResponseDto feedInfo(@RequestParam("id") @NotNull Long id) {
        return feedService.findFeedInfo(id);
    }
}