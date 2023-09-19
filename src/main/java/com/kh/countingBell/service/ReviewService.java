package com.kh.countingBell.service;

import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.domain.Review;
import com.kh.countingBell.repo.ReviewDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReviewService {

    @Autowired
    private ReviewDAO dao;

    public List<Review> showAll() {
        return dao.findAll();
    }

    public Review show(int id) {
        return dao.findById(id).orElse(null);
    }


    public Review create(Review review) {
        return dao.save(review);
    }

    public Review update(Review review) {
        Review target = dao.findById(review.getReviewCode()).orElse(null);
        if(target != null) {
            return dao.save(review);
        }
        return null;
    }

    public Review delete(int id) {
        Review target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }

    public List<Review> findByResCode(int code) { return dao.findByResCode(code); }

    public List<Review> findById(String id){ return dao.findById(id); }
}
