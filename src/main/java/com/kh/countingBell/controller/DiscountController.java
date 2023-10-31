package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.DiscountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping("/public/discount")
    public ResponseEntity<List<Discount>> showDiscountList() {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.showAll());
    }

    @GetMapping("/discount/{id}")
    public ResponseEntity<Discount> showDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.show(id));
    }

//    @PostMapping("/discount")
//    public ResponseEntity<Discount> createDiscount(@RequestBody Discount vo){
//        return ResponseEntity.status(HttpStatus.OK).body(discount.create(vo));
//    }

    @PostMapping("/discount")
    public ResponseEntity<Discount> createDiscount(@RequestParam(value = "disDesc", required = true) String disDesc,
                                                   @RequestParam(value = "disPeriod", required = true) String disPeriod,
                                                   @RequestParam(value = "resCode", required = true) Integer resCode
    ){
        log.info("disDesc:"+disDesc);
        log.info("disPeriod:"+disPeriod);
        log.info("resCode:"+resCode);

        Discount vo = new Discount();
        vo.setDisDesc(disDesc);
        vo.setDisPeriod(disPeriod);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(discountService.create(vo));
    }





    @PutMapping("/discount")
    public ResponseEntity<Discount> updateDiscount(@RequestBody Discount vo) {

        log.info("vo:"+vo);

       Discount result = discountService.update(vo);
       if(result!=null) {
           return ResponseEntity.status(HttpStatus.OK).body(result);
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }

    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Discount> deleteDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.delete(id));
    }

    // 식당별 할인 보기 : http://localhost:8080/api/discount/1/restaurant
    @GetMapping("/discount/{id}/restaurant")
    public ResponseEntity<List<Discount>> resDiscountList(@PathVariable int id) {
        log.info("discountController 식당별 할인 보기 실행");
        List<Discount> discountList = discountService.findByResCode(id);
        log.info("discountList : " + discountList);

        return ResponseEntity.ok().body(discountList);

    }


}
