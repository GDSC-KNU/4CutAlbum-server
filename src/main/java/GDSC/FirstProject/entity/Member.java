package GDSC.FirstProject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String nickName;

    private String email;

    public Member(String nickName, String email) {
        this.nickName = nickName;
        this.email = email;
    }
}