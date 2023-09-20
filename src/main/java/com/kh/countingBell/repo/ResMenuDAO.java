package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.ResMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResMenuDAO extends JpaRepository<ResMenu, Integer> {

    // 식당 1개에 따른 메뉴 조회
    @Query(value = "SELECT * FROM menu WHERE res_code = :code", nativeQuery = true)
    List<Menu> findMenuByResCode(int code);
}
