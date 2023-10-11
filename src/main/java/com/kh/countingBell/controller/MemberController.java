package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.security.TokenProvider;

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
@CrossOrigin(origins={"*"}, maxAge = 6000) // 리액트 연결 목적
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private ReviewService review;

    @Autowired
    private ReservationService reservation;

    @Autowired
    private TokenProvider tokenProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MemberDTO dto) {
        // 비밀번호 -> 암호화 처리 + 저장할 유저 만들기
        Member member = Member.builder()
                .id(dto.getId())
                .password(passwordEncoder.encode(dto.getPassword())) // 비밀번호 암호화 처리해서 가져오기
                .name(dto.getName())
                .build();

        // 서비스를 이용해 레포지토리에 유저 저장
        Member registermember = memberService.create(member);

        // 비밀번호 노출 막기 위함
        MemberDTO responseDTO = dto.builder()
                .id(registermember.getId())
                .name(registermember.getName())
                .build();

        return ResponseEntity.ok().body(responseDTO);

    }



    //사용자 id에 따른 리뷰 : GET - http://localhost:8080/api/member/1/review
    @GetMapping("/member/{user}/review")
    public ResponseEntity<List<Review>> reviewById(@PathVariable String user) {
        return ResponseEntity.status(HttpStatus.OK).body(review.findById(user));
    }

//    @GetMapping("/user")
//    public ResponseEntity<List<Member>> showAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(member.showAll());
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<Member> show(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK).body(member.show(id));
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<Member> create(@RequestBody Member vo) {
//        return ResponseEntity.status(HttpStatus.OK).body(member.create(vo));
//    }
//
//    @PutMapping("/user")
//    public ResponseEntity<Member> update(@RequestBody Member vo) {
//        Member result = member.update(vo);
//        if(result!=null) {
//            return ResponseEntity.status(HttpStatus.OK).body(result);
//        }
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//    }
//
//    @DeleteMapping("/user/{id}")
//    public ResponseEntity<Member> delete(@PathVariable String id) {
//        return ResponseEntity.status(HttpStatus.OK).body(member.delete(id));
//    }

    //사용자 id에 따른 예약 조회 : GET - http://localhost:8080/api/member/1/reservation
    @GetMapping("/member/{user}/reservation")
    public ResponseEntity<List<Reservation>> memberReservationList(@PathVariable String user) {
        log.info("user : " + user);
        return ResponseEntity.status(HttpStatus.OK).body(reservation.findById(user));
    }


}



























