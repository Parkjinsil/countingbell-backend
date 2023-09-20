package com.kh.countingBell.controller;

import com.kh.countingBell.service.ReservationService;
import com.kh.countingBell.domain.Reservation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
@Log4j2
public class ReservationController {

    @Autowired
    private ReservationService reservation;


    //예약 전체 조회 : GET - http://localhost:8080/api/reservation
    @GetMapping("/reservation")
    public  ResponseEntity<List<Reservation>> ReservationList() {
        return ResponseEntity.status(HttpStatus.OK).body(reservation.showAll());
    }

    //예약 상세 조회 : GET - http://localhost:8080/api/reservation/1
    @GetMapping("/reservation/{id}")
    public  ResponseEntity<Reservation> showReservation(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservation.show(id));
    }

    //예약 추가 : POST - http://localhost:8080/api/reservation
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation vo) {
        return ResponseEntity.status(HttpStatus.OK).body(reservation.create(vo));
    }

    //예약 수정 : PUT - http://localhost:8080/api/reservation
    @PutMapping("/reservation")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation vo) {
        return ResponseEntity.status(HttpStatus.OK).body(reservation.update(vo));
    }

    //예약 삭제 : DELETE - http://localhost:8080/api/reservation/1
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservation.delete(id));
    }
}
