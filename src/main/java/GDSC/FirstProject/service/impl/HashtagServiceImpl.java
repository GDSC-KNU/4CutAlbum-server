package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.reponseDto.hashtagsDto;
import GDSC.FirstProject.entity.Hashtag;
import GDSC.FirstProject.repository.HashtagRepository;
import GDSC.FirstProject.service.HashtagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HashtagServiceImpl implements HashtagService {

    public final HashtagRepository repository;
    @Override
    public hashtagsDto findAllHashtag() {
        List<String> result = new ArrayList<>();
        List<Hashtag> Hashtag = repository.findAll();

        for(Hashtag hashtag : Hashtag){
            result.add(hashtag.getValue());
        }

        return new hashtagsDto(result);
    }
}
