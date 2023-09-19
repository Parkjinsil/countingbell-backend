package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class FoodController {

    @Autowired
    private FoodService food;

    @GetMapping("/food")
    public ResponseEntity<List<Food>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/food/{id}")
    public ResponseEntity<Food> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/food")
    public ResponseEntity<Food> create(@RequestBody Food vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/food")
    public ResponseEntity<Food> update(@RequestBody Food vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/food/{id}")
    public ResponseEntity<Food> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(food.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/food/{id}/restaurant")
    public ResponseEntity<List<Food>> findByResCode(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(food.findByResCode(id));
    }
}
