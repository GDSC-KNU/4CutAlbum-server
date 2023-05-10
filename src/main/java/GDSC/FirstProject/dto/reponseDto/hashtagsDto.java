package GDSC.FirstProject.dto.reponseDto;

import lombok.Data;

import java.util.List;

@Data
public class hashtagsDto {
    List<String> hashtags;

    public hashtagsDto(List<String> hashtags){
        this.hashtags = hashtags;
    }
}
