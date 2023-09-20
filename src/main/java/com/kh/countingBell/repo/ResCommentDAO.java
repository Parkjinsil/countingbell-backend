package com.kh.countingBell.repo;

import com.kh.countingBell.domain.ResComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResCommentDAO extends JpaRepository<ResComment, Integer> {

    // 식당 1개에 따른 댓글 조회
    @Query(value="SELECT * FROM resComment WHERE res_code= :id", nativeQuery = true)
    List<ResComment> findByResCode(int id);

}
