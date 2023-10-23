package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.service.*;
import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Photo;
import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.service.DiscountService;
import com.kh.countingBell.service.PhotoService;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.ReservationService;
import com.kh.countingBell.service.RestaurantService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
@Log4j2
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class RestaurantController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DiscountService discount;

    @Autowired
    private PhotoService photo;

    @Autowired
    private ResCommentService comment;

    @Autowired
    private PickService pick;

    @Autowired
    private MenuService menu;

    // 식당 1개에 따른 찜 조회
    @GetMapping("/restaurant/{id}/pick")
    public ResponseEntity<List<Pick>> resPickList(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pick.findByResCode(id));
    }

    // 식당 1개에 따른 할인 조회
    // http://localhost:8080/restaurant/1/discount
    @GetMapping("/restaurant/{id}/discount")
    public ResponseEntity<List<Discount>> resDiscountList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discount.findByResCode(id));
    }

    // 식당 1개에 따른 식당사진 조회
    // http://localhost:8080/restaurant/1/photo
    @GetMapping("/restaurant/{id}/photo")
    public ResponseEntity<List<Photo>> resPhotoList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photo.findByResCode(id));
    }


    // 식당 1개의 메뉴 조회
    @GetMapping("/restaurant/{id}/menu")
    public ResponseEntity<List<Menu>> resMenuList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(menu.findByResCode(id));
    }


    // 식당 전체 조회 : GET = http://localhost:8080/api/restaurant
//    @GetMapping("/public/restaurant")
//    public ResponseEntity<List<Restaurant>> restaurantList(@RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="food", required = false) Integer food) {
//
//        // 정렬
//        Sort sort = Sort.by("resCode").descending();
//
//        // 한 페이지에 10개씩
//        Pageable pageable = PageRequest.of(page-1, 20, sort);
//
//        // 동적 쿼리를 위한 QueryDSL을 사용한 코드들 추가
//        // 1. Q도메인 클래스 가져와야 함
//        QRestaurant qRestaurant = QRestaurant.restaurant;
//
//        // 2. BooleanBuilder는 where문에 들어가는 조건들 넣어주는 컨테이너
//        BooleanBuilder builder = new BooleanBuilder();
//
//        if(food!=null) {
//            // 3. 원하는 조건은 필드값과 같이 결합해서 생성
//            BooleanExpression foodExpression = qRestaurant.food.foodCode.eq(food);
//
//
//            // 4. 만들어진 조건은 where문에 and나 or 같은 키워드와 결합한다.
//            builder.and(foodExpression);
//        }
//        Page<Restaurant> result = restaurantService.showAll(pageable, builder);
//
//        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
//
//    }


    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> showAllRestaurant() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> showRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant vo) {
        Restaurant res = new Restaurant();
        res.setResName(vo.getResName());
        res.setResAddr(vo.getResAddr());
        res.setResPhone(vo.getResPhone());
        res.setResOpenHour(vo.getResOpenHour());
        res.setResClose(vo.getResClose());
        res.setResDesc(vo.getResDesc());

        Location loc = new Location();
        loc.setLocalCode(vo.getLocation().getLocalCode());

        Food food = new Food();
        food.setFoodCode(vo.getFood().getFoodCode());

        Member mem = new Member();
        mem.setId(vo.getMember().getId());



        return ResponseEntity.ok().body(restaurantService.create(res));

    }

    @PutMapping("/restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/restaurant/{id}/location")
    public ResponseEntity<List<Restaurant>> findByResCode(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurantService.findByLocalCode(id));
    }

    //식당 1개에 따른 예약 전체 조회 : GET - http://localhost:8080/api/restaurant/1/reservation
    @GetMapping("/restaurant/{id}/reservation")
    public ResponseEntity<List<Reservation>> ReservationList(@PathVariable int id) {
        log.info("id : " + id);
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findByResCode(id));
    }
}
