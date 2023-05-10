package GDSC.FirstProject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Hashtag {

    @Id
    @Column(name = "hashtag_id")
    @GeneratedValue
    private Long id;

    private String value;

    public Hashtag(String value) {
        this.value = value;
    }
}
