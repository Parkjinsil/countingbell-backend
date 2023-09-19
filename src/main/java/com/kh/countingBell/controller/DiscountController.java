package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
public class DiscountController {

    @Autowired
    private DiscountService discount;

    @GetMapping("/discount")
    public ResponseEntity<List<Discount>> showDiscountList() {
        return ResponseEntity.status(HttpStatus.OK).body(discount.showAll());
    }

    @GetMapping("/discount/{id}")
    public ResponseEntity<Discount> showDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discount.show(id));
    }

    @PostMapping("/discount")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount vo){
        return ResponseEntity.status(HttpStatus.OK).body(discount.create(vo));
    }

    @PutMapping("/discount")
    public ResponseEntity<Discount> updateDiscount(@RequestBody Discount vo) {
       Discount result = discount.update(vo);
       if(result!=null) {
           return ResponseEntity.status(HttpStatus.OK).body(result);
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Discount> deleteDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discount.delete(id));
    }


}
