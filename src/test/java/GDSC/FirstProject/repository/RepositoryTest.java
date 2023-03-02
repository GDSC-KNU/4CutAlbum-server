package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class RepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void memberRepositoryTest() throws Exception{
        //given
        Member member1 = new Member("testUid", "nickName1", "abc");

        //when
        memberRepository.save(member1);
        Member findMember = memberRepository.findByNickName("nickName1").get();

        //then
        Assertions.assertThat(member1.getEmail()).isEqualTo(findMember.getEmail());

    }
}
