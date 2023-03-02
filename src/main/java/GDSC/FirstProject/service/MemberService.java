package GDSC.FirstProject.service;

import GDSC.FirstProject.dto.reponseDto.ProfileResponseDto;
import GDSC.FirstProject.dto.requsetDto.SignupRequestDto;


public interface MemberService {

     void signUp(SignupRequestDto requestDto);

     ProfileResponseDto loginProfile(String uid);
}
