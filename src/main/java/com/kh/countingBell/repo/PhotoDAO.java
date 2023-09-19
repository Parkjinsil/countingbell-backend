package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotoDAO extends JpaRepository<Photo, Integer> {

    // 특정 식당의 모든 사진 조회
    // SELECT * FROM photo WHERE res_code=?
    @Query(value="SELECT * FROM photo WHERE res_code= :id", nativeQuery = true)
    List<Photo> findByResCode(int id);

}
