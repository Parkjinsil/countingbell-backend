package com.kh.countingBell.service;

import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.repo.ReservationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ReservationService {

    @Autowired
    private ReservationDAO dao;

    public List<Reservation> showAll() {
        return dao.findAll();
    }

    public Reservation show(int id) {
        return dao.findById(id).orElse(null);
    }

    public Reservation create(Reservation vo) {
        return dao.save(vo);
    }

    public Reservation update(Reservation vo) {
        Reservation target = dao.findById(vo.getReserCode()).orElse(null);
        if (target != null) {
            return dao.save(vo);
        }
        return null;
    }

    public Reservation delete(int id) {
        Reservation target = dao.findById(id).orElse(null);
        dao.delete(target);
        return target;
    }

    public List<Reservation> findByResCode(int code){ return dao.findByResCode(code); }

    public List<Reservation> findById(String user){ return dao.findById(user); }
}
