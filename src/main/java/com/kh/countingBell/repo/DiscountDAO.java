package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Integer>, QuerydslPredicateExecutor<Discount> {

    // 식당별 할인 보기
    @Query(value="SELECT * FROM discount WHERE res_code= :resCode", nativeQuery = true)
    List<Discount> findByResCode(@Param("resCode")int resCode);


}
