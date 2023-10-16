package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Pick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickDAO extends JpaRepository<Pick, Integer> {

    // 식당 1개에 따른 찜 조회
    @Query(value="SELECT * FROM pick WHERE res_code= :id", nativeQuery = true)
    List<Pick> findByResCode(int id);
}
