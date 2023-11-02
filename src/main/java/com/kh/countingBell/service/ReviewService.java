package com.kh.countingBell.service;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Review;
import com.kh.countingBell.repo.ReviewDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewDAO reviewDAO;

    public Page<Review> showAll(Pageable pageable) {

        return reviewDAO.findAll(pageable);
    }
    public List<Review> showAll() {
        return reviewDAO.findAll();
    }

    public Review show(int id) {
        return reviewDAO.findById(id).orElse(null);
    }


    public Review create(Review review) {
        return reviewDAO.save(review);
    }

    public Review update(Review review) {
        Review target = reviewDAO.findById(review.getReviewCode()).orElse(null);
        if(target != null) {
            return reviewDAO.save(review);
        }
        return null;
    }

    public Review delete(int reviewCode) {
        Review target = reviewDAO.findById(reviewCode).orElse(null);
        reviewDAO.delete(target);
        return target;
    }

    // 식당별 리뷰
    public List<Review> findReviewByResCode(int resCode) { return reviewDAO.findReviewByResCode(resCode); }

    // id별 리뷰
    public List<Review> findReviewById(String id){ return reviewDAO.findReviewById(id); }

}
