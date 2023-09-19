package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationDAO extends JpaRepository<Reservation,Integer> {

    //식당 1개에 따른 예약 전체 조회
    //@Query(value = "SELECT * F")
}
