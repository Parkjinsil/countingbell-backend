package com.kh.countingBell.service;

import com.kh.countingBell.domain.ResComment;
import com.kh.countingBell.repo.ResCommentDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResCommentService {

    @Autowired
    private ResCommentDAO resCommentDAO;

    public List<ResComment> showAllResComment() { // SELECT * FROM COMMENT;

        return resCommentDAO.findAll();
    }

    public ResComment showResComment(int code) {

        return resCommentDAO.findById(code).orElse(null);
    }

    public ResComment createResComment(ResComment rescomment){
        return resCommentDAO.save(rescomment);
    }

    public ResComment updateResComment(ResComment rescomment) {
        ResComment target = resCommentDAO.findById(rescomment.getResCommentCode()).orElse(null);
        if(target!=null) {
            return resCommentDAO.save(rescomment);
        }
        return null;
    }

    // DELETE FROM COMMENT WHERE CODE=?
    public ResComment deleteResComment(int code){
        ResComment target = resCommentDAO.findById(code).orElse(null);
        resCommentDAO.delete(target);
        return target;
    }

    public List<ResComment> findByReviewCode(int code) {
        return resCommentDAO.findByReviewCode(code);
    }
}
