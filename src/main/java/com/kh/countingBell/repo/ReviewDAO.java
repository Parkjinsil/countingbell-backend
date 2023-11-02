package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ReviewDAO extends JpaRepository<Review,Integer> {

    //식당 1개에 따른 리뷰
    @Query(value = "SELECT * FROM review WHERE res_code = :resCode", nativeQuery = true)
    List<Review> findReviewByResCode(@Param("resCode") int resCode);

    //사용자 id에 따른 리뷰
    @Query(value = "SELECT * FROM review WHERE id = :id", nativeQuery = true)
    List<Review> findReviewById(@Param("id") String id);

    //평점 ?인 리뷰 조회
    @Query(value = "SELECT * FROM REVIEW WHERE review_grade = :grade", nativeQuery = true)
    List<Review> reviewByGrade(@Param("grade") Integer grade);

}
