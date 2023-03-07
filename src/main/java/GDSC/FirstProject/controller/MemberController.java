package GDSC.FirstProject.controller;

import GDSC.FirstProject.dto.reponseDto.ProfileResponseDto;
import GDSC.FirstProject.dto.requsetDto.SignupRequestDto;
import GDSC.FirstProject.message.ResponseMessage;
import GDSC.FirstProject.message.ResponseService;
import GDSC.FirstProject.message.SingleResponse;
import GDSC.FirstProject.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final ResponseService responseService;

    @GetMapping("/profile")
    public SingleResponse<ProfileResponseDto> getLoginProfile(@RequestParam String uid) {
        ProfileResponseDto dto = memberService.loginProfile(uid);
        return responseService.getSingleResponse(dto);
    }

    @PostMapping("/signup")
    public ResponseMessage signUp(@RequestBody SignupRequestDto requestDto) {
        memberService.signUp(requestDto);
        return responseService.getSuccessResponse();
    }

    @GetMapping("/signup-check")
    public SingleResponse<Boolean> signupCheck(@RequestParam String uid) {
        Boolean check = memberService.signupCheck(uid);
        return responseService.getSingleResponse(check);
    }
}
