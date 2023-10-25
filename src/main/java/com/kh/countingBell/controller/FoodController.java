package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.service.FoodService;
import com.kh.countingBell.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin(origins={"*"}, maxAge = 6000)
@Slf4j
public class FoodController {

    @Autowired
    private FoodService food;

    @Autowired
    private RestaurantService restaurantService;

    // 음식타입 전체 조회 : GET =  http://localhost:8080/api/public/food?page=1
    @GetMapping("/public/food")
    public ResponseEntity<List<Food>> showAllFood(@RequestParam(name="page", defaultValue = "1") int page ) {
        // 정렬
        Sort sort = Sort.by("foodCode").descending();

        // 한 페이지에 10개
        Pageable pageable = PageRequest.of(page-1, 10, sort);

        Page<Food> result = food.showAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());


    }





    @GetMapping("/food/{id}")
    public ResponseEntity<Food> showFood(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/food")
    public ResponseEntity<Food> createFood(@RequestBody Food vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/food")
    public ResponseEntity<Food> updateFood(@RequestBody Food vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }





}
