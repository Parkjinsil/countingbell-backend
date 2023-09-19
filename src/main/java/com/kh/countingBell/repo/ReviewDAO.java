package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewDAO extends JpaRepository<Review,Integer> {

}
