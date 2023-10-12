package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountDAO extends JpaRepository<Discount, Integer> {

    // 특정 식당의 모든 할인 조회
    // SELECT * FROM discount WHERE res_code=?
    @Query(value="SELECT * FROM discount WHERE res_code= :id", nativeQuery = true)
    List<Discount> findByResCode(int id);

}
