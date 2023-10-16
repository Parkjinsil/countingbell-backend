package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Restaurant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RestaurantDAO extends JpaRepository<Restaurant, Integer> {

    // 지역에 따른 식당 조회
    @Query(value = "SELECT * FROM restaurant WHERE local_code = :code", nativeQuery = true)
    List<Restaurant> findByLocalCode(int code); // 이곳의 code가 쿼리문 안의 '?' 자리로 들어간다  ==> service로 메서드 추가하러 가기


    // 음식종류에 따른 식당 조회
    @Query(value = "SELECT * FROM restaurant WHERE food_code = :code", nativeQuery = true)
    List<Restaurant> findResByFood(int code);






}
