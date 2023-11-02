package com.kh.countingBell.repo;


import com.kh.countingBell.domain.Restaurant;
import org.springframework.data.domain.Page;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

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

    @Transactional
    @Modifying
    @Query(value = "UPDATE RESTAURANT SET RES_PICKS = (RES_PICKS + 1) WHERE RES_CODE = :resCode",nativeQuery = true)
    int updatePicks(@Param("resCode") int resCode);

    @Transactional
    @Modifying
    @Query(value = "UPDATE RESTAURANT SET RES_PICKS = (RES_PICKS - 1) WHERE RES_CODE = :resCode", nativeQuery = true)
    int deletePicks(@Param("resCode") int resCode);


    // 아이디별 식당조회
    @Query(value = "SELECT * FROM restaurant WHERE id = :id", nativeQuery = true)
    List<Restaurant> getResByUserId(@Param("id") String id);

    // 식당명으로 식당검색
    @Query(value = "SELECT * FROM restaurant WHERE res_Name LIKE %:keyword%", nativeQuery = true)
    List<Restaurant> searchResByName(@Param("keyword") String keyword);
    




}
