package com.kh.countingBell.service;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.repo.DiscountDAO;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

    // 전체 할인 조회
    public Page<Discount> showAll(Pageable pageable, BooleanBuilder builder) {
        return discountDAO.findAll(builder, pageable);
    }

    // 상세 할인 조회
    public Discount show(int id) {
        Discount discount = discountDAO.findById(id).orElse(null);
        return discount;
    }

    //할인 추가
    public Discount create(Discount discount) {
        return discountDAO.save(discount);
    }

    //할인 수정
    public Discount update(Discount discount) {
        Discount target = discountDAO.findById(discount.getDisCode()).orElse(null);
        if(target!=null){
            return discountDAO.save(discount);
        }
        return null;
    }

    // 할인 삭제
    public Discount delete(int id){
        Discount target = discountDAO.findById(id).orElse(null);
        discountDAO.delete(target);
        return target;
    }

    // 식당별 할인 보기
    public List<Discount> findByResCode(int id) {
        return discountDAO.findByResCode(id);
    }


}
