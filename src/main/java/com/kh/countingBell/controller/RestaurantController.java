package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurant;

    @Autowired
    private DiscountService discount;

    @Autowired
    private PhotoService photo;

    @Autowired
    private PickService pick;


    // 식당 1개에 따른 찜 조회
    // http://localhost:8080/api/restaurant/1/pick
    @GetMapping("/restaurant/{id}/pick")
    public ResponseEntity<List<Pick>> resPickList(@PathVariable int id) {
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
    public ResponseEntity<List<Photo>> resPhotoList(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(photo.findByResCode(id));
    }

    @GetMapping("/restaurant")
    public ResponseEntity<List<Restaurant>> showAllRestaurant() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> showRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }}

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurant.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
