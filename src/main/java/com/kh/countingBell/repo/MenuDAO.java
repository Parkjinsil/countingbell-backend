package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDAO extends JpaRepository<Menu, Integer>, QuerydslPredicateExecutor<Menu> {

    // 식당별 메뉴 조회
    @Query(value = "SELECT * FROM menu WHERE res_code = :resCode", nativeQuery = true)
    List<Menu> findByResCode(@Param("resCode") int resCode);

    // 음식명에 따른 검색 쿼리를 작성
    @Query(value = "SELECT * FROM menu WHERE menu_name LIKE %:keyword%", nativeQuery = true)
    List<Menu> searchResByMenuName(@Param("keyword") String keyword);

}
