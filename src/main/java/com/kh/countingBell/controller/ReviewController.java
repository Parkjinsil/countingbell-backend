package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.service.MemberService;
import com.kh.countingBell.service.ResCommentService;
import com.kh.countingBell.service.RestaurantService;
import com.kh.countingBell.service.ReviewService;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/api/*")
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class ReviewController {

    @Value("${countingbell.upload.path}")
    private String uploadPath;

    @Autowired
    private ReviewService reviewService;

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
    public  ResponseEntity<List<Review>> ReviewList(@RequestParam(name="page", defaultValue = "1")int page) {

        Sort sort = Sort.by("reviewCode").descending();
        Pageable pageable = PageRequest.of(page-1, 10, sort);

        Page<Review> result = reviewService.showAll(pageable);

        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }

    //리뷰 상세 조회 : GET - http://localhost:8080/api/review/1
    @GetMapping("/review/{id}")
    public  ResponseEntity<Review> showReview(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.show(id));
    }

    //리뷰 추가 : POST - http://localhost:8080/api/review
    @PostMapping("/review")
    public ResponseEntity<Review> createReview(String reviewContent,
                                               Integer reviewGrade,
                                               @RequestPart(value = "reviewPhoto", required = true) MultipartFile reviewPhoto,
                                               String id,
                                               Integer resCode) {

        String originalPhoto = reviewPhoto.getOriginalFilename();
        String realImage = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);
        String uuid = UUID.randomUUID().toString();
        String savePhoto = uploadPath + File.separator + uuid + "_" + realImage;
        Path pathPhoto = Paths.get(savePhoto);

        try {
            reviewPhoto.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Review vo = new Review();
        vo.setReviewPhoto(uuid + "_" + realImage);
        vo.setReviewContent(reviewContent);
        vo.setReviewGrade(reviewGrade);

        Member mem = new Member();
        mem.setId(id);
        vo.setMember(mem);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(reviewService.create(vo));
    }

    //리뷰 수정 : PUT - http://localhost:8080/api/review
    @PutMapping("/review")
    public ResponseEntity<Review> updateReview(@RequestParam(value = "reviewCode", required = true) Integer reviewCode,
                                               @RequestParam(value = "resCode", required = true) Integer resCode,
                                               @RequestPart(value = "reviewPhoto", required = true) MultipartFile reviewPhoto,
                                               @RequestParam(value = "reviewContent", required = true) String reviewContent,
                                               @RequestParam(value = "reviewGrade", required = true) Integer reviewGrade,
                                               @RequestParam(value = "id", required = true) String id,
                                               @RequestParam(value="reviewDate", required = true) Date reviewDate) {
        String originalPhoto = reviewPhoto.getOriginalFilename();
        String realImage = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);
        String uuid = UUID.randomUUID().toString();
        String savePhoto = uploadPath + File.separator + uuid + "_" + realImage;
        Path pathPhoto = Paths.get(savePhoto);

        try {
            reviewPhoto.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Review vo = new Review();
        vo.setReviewCode(reviewCode);
        vo.setReviewContent(reviewContent);
        vo.setReviewGrade(reviewGrade);
        vo.setReviewDate(reviewDate);
        vo.setReviewPhoto(uuid +  "_" + realImage);

        Restaurant res = new Restaurant();
        res.setResCode(resCode);
        vo.setRestaurant(res);

        Member mem = new Member();
        mem.setId(id);
        vo.setMember(mem);

        return ResponseEntity.status(HttpStatus.OK).body(reviewService.update(vo));
    }

    //리뷰 삭제 : DELETE - http://localhost:8080/api/review/1
    @DeleteMapping("/review/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reviewService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

