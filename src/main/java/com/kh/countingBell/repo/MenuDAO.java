package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDAO extends JpaRepository<Menu, Integer>, QuerydslPredicateExecutor<Menu> {

    // 식당별 메뉴 조회
    @Query(value = "SELECT * FROM menu WHERE res_code = :code", nativeQuery = true)
    List<Menu> findByResCode(int code);
}
