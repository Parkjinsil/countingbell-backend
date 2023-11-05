package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation,Integer> {

    //식당 1개에 따른 예약 전체 조회
    @Query(value = "SELECT * FROM reservation WHERE res_code = :resCode", nativeQuery = true)
    List<Reservation> findByResCode(@Param("resCode") int resCode);


    @Query(value = "SELECT * FROM reservation WHERE id = :id", nativeQuery = true)
    List<Reservation> findReserById(@Param("id") String id);
}
