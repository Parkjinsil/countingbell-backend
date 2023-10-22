package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.security.TokenProvider;
import com.kh.countingBell.service.DiscountService;
import com.kh.countingBell.service.MemberService;

import com.kh.countingBell.service.ReviewService;
import com.kh.countingBell.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class MemberController {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReviewService review;

    @Autowired
    private ReservationService reservation;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    //    // 회원가입
    @PostMapping("/user/signup")
    public ResponseEntity register(@RequestBody MemberDTO dto) {
        log.info("dto" + dto);
        // 비밀번호 -> 암호화 처리 + 저장할 유저 만들기
        Member member = Member.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword()))
                .name(dto.getName())
                .phone(dto.getPhone())
                .nickname(dto.getNickname())
                .gender(dto.getGender())
                .age(dto.getAge())
                .email(dto.getEmail())
                .role(dto.getRole())
                .build();

        // 서비스를 이용해 리포지터리에 유저 저장
        Member registerMember = memberService.create(member);
        MemberDTO responseDTO = dto.builder()
                .id(registerMember.getId())
                .name(registerMember.getName())
                .phone(registerMember.getPhone())
                .nickname(registerMember.getNickname())
                .gender(registerMember.getGender())
                .age(registerMember.getAge())
                .email(registerMember.getEmail())
                .role(registerMember.getRole())
                .build();
        return ResponseEntity.ok().body(responseDTO);
    }

    // 로그인 -> token
    @PostMapping("/user/signin")
    public ResponseEntity authenticate(@RequestBody MemberDTO dto) {
        Member member = memberService.getByCredentials(dto.getId(), dto.getPassword(), passwordEncoder);
        if (member != null) { // -> 토큰 생성
            String token = tokenProvider.create(member);
            MemberDTO responseDTO = MemberDTO.builder()
                    .id(member.getId())
                    .name(member.getName())
                    .token(token)
                    .build();
            return ResponseEntity.ok().body(responseDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    // 아이디 찾기
    @PostMapping("/searchId")
    public ResponseEntity<String> searchId(@RequestBody MemberDTO memberDTO) {

        log.info("memberController 아이디 찾기 실행");
        log.info(memberDTO.toString());
        String userId = memberService.searchId(memberDTO);
        log.info(userId);

        return ResponseEntity.ok().body(userId);
    }

    // 패스워드 찾기 :: 수정해야 함!!!!!!!!///////////////////////
//    @PostMapping("user/searchPwd")
//    public ResponseEntity<String> searchPwd(@RequestBody MemberDTO memberDTO) {
//        String userPwd = memberService.searchPwd(memberDTO);
////        log.info("DB저장되어 있는 PWD : " + userPwd);
////        log.info("랜덤하게 생성한 비밀번호 : " + tempPwd);
//
//        return null;
//    }



    //사용자 id에 따른 리뷰 : GET - http://localhost:8080/api/member/1/review
    @GetMapping("/member/{user}/review")
    public ResponseEntity<List<Review>> reviewById(@PathVariable String user) {
        return ResponseEntity.status(HttpStatus.OK).body(review.findById(user));
    }

    //사용자 id에 따른 예약 조회 : GET - http://localhost:8080/api/member/1/reservation
    @GetMapping("/member/{user}/reservation")
    public ResponseEntity<List<Reservation>> memberReservationList(@PathVariable String user) {
        log.info("user : " + user);
        return ResponseEntity.status(HttpStatus.OK).body(reservation.findById(user));
    }

    // 멤버전체 보기
    @GetMapping("/user")
    public ResponseEntity<List<Member>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.showAll());
    }

    // 멤버 1명 조회
    @GetMapping("/user/{id}")
    public ResponseEntity<Member> show(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.show(id));
    }





}