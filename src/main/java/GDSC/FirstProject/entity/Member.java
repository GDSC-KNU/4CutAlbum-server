package GDSC.FirstProject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long id;

    private String uid;

    private String nickName;

    private String email;

    @CreatedDate
    private LocalDateTime CreatedTime;

    @OneToMany(mappedBy = "member", orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feed> feedList = new ArrayList<>();

    public Member(String uid, String nickName, String email) {
        this.uid = uid;
        this.nickName = nickName;
        this.email = email;
    }
}