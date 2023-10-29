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

    public Reservation create(Reservation reser) {
        return dao.save(reser);
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

    public List<Reservation> findByResCode(int code){
        log.info("code : " + code);
        return dao.findByResCode(code);
    }

    // 사용자 id에 따른 예약 조회
    public List<Reservation> findReserById(String id){
        return dao.findReserById(id); }
}
