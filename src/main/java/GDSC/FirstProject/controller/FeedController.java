package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.requsetDto.UpdateFeedRequestDto;
import GDSC.FirstProject.dto.reponseDto.feedInfoResponseDto;
import GDSC.FirstProject.dto.reponseDto.feedListResponseDto;
import GDSC.FirstProject.dto.requsetDto.createFeedRequestDto;
import GDSC.FirstProject.message.ResponseService;
import GDSC.FirstProject.message.SingleResponse;
import GDSC.FirstProject.service.FeedService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feeds")
@RequiredArgsConstructor
@Validated
@Slf4j
public class FeedController {

    private final FeedService feedService;
    private final ResponseService responseService;

    @PutMapping
    public SingleResponse<Long> updateFeed(@RequestBody UpdateFeedRequestDto requestDto) {
        Long updateFeedID = feedService.updateFeed(requestDto);
        return responseService.getSingleResponse(updateFeedID);
    }

    @PostMapping
    public Map<String, Long> saveFeed(@RequestBody createFeedRequestDto requestDto){
        Map<String, Long> response = new HashMap<>();
        Long saveFeeID = feedService.saveFeed(requestDto);
        response.put("saveFeedID", saveFeeID);
        return response;
    }
    @GetMapping("/list")
    public feedListResponseDto feedList(
            @RequestParam(name = "company") String company,
            @RequestParam(name = "people-count") String people_count,
            @RequestParam(name = "hashtags") List<String> hashtags,
            @RequestParam("page-number") @NotNull Long page_number) {

        return feedService.makeFeedListResponseDto(company, feedService.translatePeopleCount(people_count), hashtags, page_number);
    }

    @GetMapping("/")
    public feedInfoResponseDto feedInfo(@RequestParam("id") @NotNull Long id) {
        return feedService.findFeedInfo(id);
    }

    @DeleteMapping("/")
    public void deleteFeed(@RequestParam("id") @NotNull Long id) {
        feedService.deleteFeedById(id);

    }
}