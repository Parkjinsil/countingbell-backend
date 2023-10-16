package com.kh.countingBell.repo;

import com.kh.countingBell.domain.ResComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ResCommentDAO extends JpaRepository<ResComment, Integer> {

    // 리뷰 1개에 따른 댓글 조회
    @Query(value="SELECT * FROM res_comment WHERE review_code= :id", nativeQuery = true)
    List<ResComment> findByReviewCode(int id);

}
