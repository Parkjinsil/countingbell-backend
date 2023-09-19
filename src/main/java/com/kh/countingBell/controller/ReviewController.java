package com.kh.countingBell.controller;

import com.kh.countingBell.service.ReviewService;
import com.kh.countingBell.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class ReviewController {

    @Autowired
    private ReviewService review;

    @Autowired
    private RestaurantService restaurant;

    @Autowired
    private MemberService member;

    //리뷰 전체 조회 : GET - http://localhost:8080/api/review
    @GetMapping("/review")
    public  ResponseEntity<List<Review>> ReviewList() {
        return ResponseEntity.status(HttpStatus.OK).body(review.showAll());
    }

    //리뷰 상세 조회 : GET - http://localhost:8080/api/review/1
    @GetMapping("/review/{id}")
    public  ResponseEntity<Review> showReview(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(review.show(id));
    }

    //식당 1개에 따른 리뷰 : GET - http://localhost:8080/api/review/1/restaurant
    @GetMapping("/restaurant/{id}/review")
    public ResponseEntity<List<Review>> ReviewList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(review.findByResCode(id));
    }

    //사용자 id에 따른 리뷰 : GET - http://localhost:8080/api/member/1/review
    @GetMapping("/member/{id}/review")
    public ResponseEntity<List<Review>> memberReviewList(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(review.findById(id));
    }

    //리뷰 추가 : POST - http://localhost:8080/api/review
    @PostMapping("/review")
    public ResponseEntity<Review> createReview(@RequestBody Review vo) {
        return ResponseEntity.status(HttpStatus.OK).body(review.create(vo));
    }

    //리뷰 수정 : PUT - http://localhost:8080/api/review
    @PutMapping("/review")
    public ResponseEntity<Review> updateReview(@RequestBody Review vo) {
        return ResponseEntity.status(HttpStatus.OK).body(review.update(vo));
    }

    //리뷰 삭제 : DELETE - http://localhost:8080/api/review/1
    @DeleteMapping("/review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(review.delete(id));
    }

}
