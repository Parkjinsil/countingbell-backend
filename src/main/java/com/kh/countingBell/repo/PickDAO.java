package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Pick;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PickDAO extends JpaRepository<Pick, Integer> {

    // 식당 1개에 따른 찜 조회
    @Query(value="SELECT * FROM pick WHERE res_code= :resCode", nativeQuery = true)
    List<Pick> findByResCode(int resCode);

    @Query(value = "SELECT * FROM PICK WHERE ID = :id AND RES_CODE = :resCode", nativeQuery = true)
    Pick findByIdAndRestaurant(String id, int resCode);


}
