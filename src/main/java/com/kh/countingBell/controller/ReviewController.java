package com.kh.countingBell.controller;

import com.kh.countingBell.domain.ResComment;
import com.kh.countingBell.service.MemberService;
import com.kh.countingBell.service.ResCommentService;
import com.kh.countingBell.service.RestaurantService;
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

    @Autowired
    private ResCommentService resComment;

    // 리뷰 1개에 따른 댓글 조회
    @GetMapping("/review/{id}/comment")
    public ResponseEntity<List<ResComment>> resCommentList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(resComment.findByReviewCode(id));
    }

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
