package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Member;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.MemberService;
import com.kh.countingBell.service.ReservationService;
import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.service.RestaurantService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/*")
@Slf4j
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    //예약 전체 조회 : GET - http://localhost:8080/api/reservation
    @GetMapping("/reservation")
    public  ResponseEntity<List<Reservation>> ReservationList() {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.showAll());
    }

    //예약 상세 조회 : GET - http://localhost:8080/api/reservation/1
    @GetMapping("/reservation/{id}")
    public  ResponseEntity<Reservation> showReservation(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.show(id));
    }

    //예약 추가 : POST - http://localhost:8080/api/reservation
    @PostMapping("/reservation")
    public ResponseEntity<Reservation> createReservation(Integer reserPer,
                                                         String id,
                                                         Integer resCode,
                                                         @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date reserDate,
                                                         String reserTime) {

        Reservation vo = new Reservation();
        vo.setReserPer(reserPer);
        vo.setReserDate(reserDate);
        vo.setReserTime(reserTime);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        Member mem = new Member();
        mem.setId(id);
        vo.setMember(mem);

        return ResponseEntity.status(HttpStatus.OK).body(reservationService.create(vo));
    }

    //예약 수정 : PUT - http://localhost:8080/api/reservation
    @PutMapping("/reservation")
    public ResponseEntity<Reservation> updateReservation(@RequestBody Reservation vo) {
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.update(vo));
    }

    //예약 삭제 : DELETE - http://localhost:8080/api/reservation/1
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Reservation> deleteReservation(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(reservationService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
