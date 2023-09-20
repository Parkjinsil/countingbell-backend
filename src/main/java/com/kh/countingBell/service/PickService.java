package com.kh.countingBell.service;

import com.kh.countingBell.domain.Pick;
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

    public Pick show(int code) {

        return pickDAO.findById(code).orElse(null);
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

    public Pick delete(int code) {
        Pick target = pickDAO.findById(code).orElse(null);
        pickDAO.delete(target);
        return target;
    }

    public List<Pick> findByResCode(int code) {

        return pickDAO.findByResCode(code);
    }

}
