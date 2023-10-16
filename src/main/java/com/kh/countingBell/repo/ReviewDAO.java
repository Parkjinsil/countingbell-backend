package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewDAO extends JpaRepository<Review,Integer> {

    //식당 1개에 따른 리뷰
    @Query(value = "SELECT * FROM review WHERE res_code = :code", nativeQuery = true)
    List<Review> findByResCode(int code);

    //사용자 id에 따른 리뷰
    @Query(value = "SELECT * FROM review WHERE id = :user", nativeQuery = true)
    List<Review> findById(String user);

}
