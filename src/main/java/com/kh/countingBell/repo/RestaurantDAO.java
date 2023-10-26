package com.kh.countingBell.repo;


import com.kh.countingBell.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant, Integer>, QuerydslPredicateExecutor<Restaurant> {

    // 지역별 식당 조회
    @Query(value = "SELECT * FROM restaurant WHERE local_code = :localCode", nativeQuery = true)
    List<Restaurant> findByLocalCode(@Param("localCode") int localCode);
                                                      // 이곳의 code가 쿼리문 안의 '?' 자리로 들어간다  ==> service로 메서드 추가하러 가기


    // 음식종류에 따른 식당 조회
    @Query(value = "SELECT * FROM restaurant WHERE food_code = :foodCode", nativeQuery = true)
    List<Restaurant> findResByFood(@Param("foodCode") int foodCode);

    // 음식 + 종류에 따른 식당 조회 ( 빠른예약 )
    @Query(value = "SELECT * FROM restaurant WHERE food_code = :foodCode AND local_code = :localCode", nativeQuery = true)
    List<Restaurant> findResByFilter(@Param("foodCode") int foodCode, @Param("localCode") int localCode);






}
