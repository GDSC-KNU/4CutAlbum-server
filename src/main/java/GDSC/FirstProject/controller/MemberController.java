package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.reponseDto.ProfileResponseDto;
import GDSC.FirstProject.dto.requsetDto.SignupRequestDto;
import GDSC.FirstProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/profile")
    public ProfileResponseDto getLoginProfile(@RequestParam String uid) {
        System.out.println(uid);
        return memberService.loginProfile(uid);
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody SignupRequestDto requestDto) {
        memberService.signUp(requestDto);
    }

}
