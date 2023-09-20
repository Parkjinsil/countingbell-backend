package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Member;
import com.kh.countingBell.domain.Review;
import com.kh.countingBell.service.DiscountService;
import com.kh.countingBell.service.MemberService;
import com.kh.countingBell.service.PhotoService;
import com.kh.countingBell.service.ReviewService;
import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
public class MemberController {

    @Autowired
    private MemberService member;

    @Autowired
    private ReviewService review;

    @Autowired
    private ReservationService reservation;

    //사용자 id에 따른 리뷰 : GET - http://localhost:8080/api/member/1/review
    @GetMapping("/member/{user}/review")
    public ResponseEntity<List<Review>> reviewById(@PathVariable String user) {
        return ResponseEntity.status(HttpStatus.OK).body(review.findById(user));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Member>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(member.showAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Member> show(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(member.show(id));
    }

    @PostMapping("/user")
    public ResponseEntity<Member> create(@RequestBody Member vo) {
        return ResponseEntity.status(HttpStatus.OK).body(member.create(vo));
    }

    @PutMapping("/user")
    public ResponseEntity<Member> update(@RequestBody Member vo) {
        Member result = member.update(vo);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Member> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(member.delete(id));
    }

    //사용자 id에 따른 예약 조회 : GET - http://localhost:8080/api/member/1/reservation
    @GetMapping("/member/{user}/reservation")
    public ResponseEntity<List<Reservation>> memberReservationList(@PathVariable String user) {
        log.info("user : " + user);
        return ResponseEntity.status(HttpStatus.OK).body(reservation.findById(user));
    }


}



























