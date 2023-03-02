package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(Long id);

    Optional<Member> findByUid(String uid);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickName(String nick_name);
}