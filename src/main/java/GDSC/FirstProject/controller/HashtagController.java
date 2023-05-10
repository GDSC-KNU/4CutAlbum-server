package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.reponseDto.hashtagsDto;
import GDSC.FirstProject.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HashtagController {

    private final HashtagService service;

    @GetMapping("/hashtags")
    public hashtagsDto getHashtags(){
        return service.findAllHashtag();
    }
}
