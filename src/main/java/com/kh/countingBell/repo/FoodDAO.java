package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface FoodDAO extends JpaRepository<Food, Integer> {

    // 식당 1개에 따른 음식 종류 조회
    @Query(value = "SELECT * FROM food WHERE res_code = :code", nativeQuery = true)
    List<Food> findByResCode(int code);

}
