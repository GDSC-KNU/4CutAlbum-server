package GDSC.FirstProject.service.impl;

import GDSC.FirstProject.dto.reponseDto.ProfileResponseDto;
import GDSC.FirstProject.dto.requsetDto.SignupRequestDto;
import GDSC.FirstProject.entity.Member;
import GDSC.FirstProject.repository.MemberRepository;
import GDSC.FirstProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void signUp(SignupRequestDto requestDto) {
        if (duplicateMemberCheck(requestDto.getUid())) {
            throw new RuntimeException("이미 가입된 회원입니다.");
        }

        Member member = new Member(requestDto.getUid(), requestDto.getNickName(), requestDto.getEmail());
        memberRepository.save(member);

    }

    @Override
    public ProfileResponseDto loginProfile(String uid) {

        Member member = memberRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        return new ProfileResponseDto(member);
    }

    private boolean duplicateMemberCheck(String uid) {
        return memberRepository.findByUid(uid).isPresent();
    }
}
