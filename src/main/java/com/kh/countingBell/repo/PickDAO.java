package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Pick;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickDAO extends JpaRepository<Pick, Integer>, QuerydslPredicateExecutor<Pick> {

    // 식당 1개에 따른 찜 조회
    @Query(value="SELECT * FROM pick WHERE res_code= :resCode", nativeQuery = true)
    List<Pick> findByResCode(@Param("resCode")int resCode);

    // 사용자 식당 찜 중복 제거
    @Query(value = "SELECT * FROM PICK WHERE ID = :id AND RES_CODE = :resCode", nativeQuery = true)
    Pick findByIdAndRestaurant(@Param("id")String id, @Param("resCode") int resCode);

    // 사용자별 찜 조회
    @Query(value = "SELECT * FROM PICK WHERE ID = :id", nativeQuery = true)
    List<Pick> findByMember(@Param("id") String id);

}
