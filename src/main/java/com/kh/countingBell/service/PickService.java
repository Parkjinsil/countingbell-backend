package com.kh.countingBell.service;

import com.kh.countingBell.domain.Pick;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.repo.PickDAO;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PickService {

    @Autowired
    private PickDAO pickDAO;

    public Page<Pick> showAll(Pageable pageable, BooleanBuilder builder) {
        return pickDAO.findAll(builder, pageable);
    }

    // 찜 보기
    public Pick show(int pickCode) {
        return pickDAO.findById(pickCode).orElse(null);
    }

    // 사용자에 따른 찜조회
    public List<Pick> findPickById(String id) {
        return pickDAO.findByMember(id);
    }

    // 사용자 식당 찜 중복 제거
    public Pick findByIdAndRestaurant(String id, int resCode){
        return pickDAO.findByIdAndRestaurant(id, resCode);
    }

    // 찜 추가
    public Pick create(Pick pick) {
        return pickDAO.save(pick);
    }

    // 식당 찜 (찜수 + )
    public Pick update(Pick pick) {
        Pick target = pickDAO.findById(pick.getPickCode()).orElse(null);
        if(target!=null) {
            return pickDAO.save(pick);
        }
        return null;
    }

    // 찜 삭제
    public Pick delete(int pickCode) {
        Pick pick = pickDAO.findById(pickCode).orElse(null);
        pickDAO.delete(pick);
        return pick;
    }

    // 식당 1개에 따른 찜 조회
    public List<Pick> findByResCode(int id) {
        return pickDAO.findByResCode(id);
    }



}


