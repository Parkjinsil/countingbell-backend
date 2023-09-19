package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Member;
import com.kh.countingBell.service.DiscountService;
import com.kh.countingBell.service.MemberService;
import com.kh.countingBell.service.PhotoService;
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
    private MemberService memberService;

    @GetMapping("/user")
    public ResponseEntity<List<Member>> showAll() {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.showAll());
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Member> show(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.show(id));
    }

    @PostMapping("/user")
    public ResponseEntity<Member> create(@RequestBody Member member) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.create(member));
    }

    @PutMapping("/user")
    public ResponseEntity<Member> update(@RequestBody Member member) {
        Member result = memberService.update(member);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Member> delete(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(memberService.delete(id));
    }


}


























