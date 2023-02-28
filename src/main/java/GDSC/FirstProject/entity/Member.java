package GDSC.FirstProject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String uid;

    private String nickName;

    private String email;

    private LocalDateTime CreatedTime;

    @OneToMany(mappedBy = "member")
    private List<Feed> feedList = new ArrayList<>();

    public Member(String uid, String nickName, String email) {
        this.uid = uid;
        this.nickName = nickName;
        this.email = email;
    }
}