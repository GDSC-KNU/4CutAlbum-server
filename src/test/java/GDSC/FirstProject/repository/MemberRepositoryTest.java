package GDSC.FirstProject.repository;

import GDSC.FirstProject.entity.Feed;
import GDSC.FirstProject.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void findMemberTest() throws Exception{
        //given
        Long uid = 1L;

        //when
        Member findMember = memberRepository.findById(uid).orElseThrow(() -> new Exception("회원이 존재하지 않습니다."));

        //then
        assertThat(findMember).isNotNull();
        List<Feed> feedList = findMember.getFeedList();
        for (Feed feed : feedList) {
            System.out.println("feed = " + feed.toString());
        }

    }
}