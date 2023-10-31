package com.kh.countingBell.service;

import com.kh.countingBell.domain.Pick;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.repo.PickDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PickService {

    @Autowired
    private PickDAO pickDAO;

    public List<Pick> showAll() {
        return pickDAO.findAll();
    }

    public Pick show(int pickCode) {
        return pickDAO.findById(pickCode).orElse(null);
    }

    public Pick findByIdAndRestaurant(String id, int resCode){
        return pickDAO.findByIdAndRestaurant(id, resCode);
    }

    public Pick create(Pick pick) {
        return pickDAO.save(pick);
    }

    public Pick update(Pick pick) {
        Pick target = pickDAO.findById(pick.getPickCode()).orElse(null);
        if(target!=null) {
            return pickDAO.save(pick);
        }
        return null;
    }

    public Pick delete(int pickCode) {
        Pick pick = pickDAO.findById(pickCode).orElse(null);
        pickDAO.delete(pick);
        return pick;
    }

    // 식당 1개에 따른 찜 조회
    public List<Pick> findByResCode(int code) {
        return pickDAO.findByResCode(code);
    }



}


