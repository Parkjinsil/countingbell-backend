package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.service.DiscountService;
import com.querydsl.core.BooleanBuilder;
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

    // 전체 할인 조회 : http://localhost:8080/api/public/discount
    @GetMapping("/public/discount")
    public ResponseEntity<List<Discount>> showDiscountList(@RequestParam(name = "page", defaultValue = "1") int page
    ) {
        Sort sort = Sort.by("disCode").descending();
        Pageable pageable = PageRequest.of(page - 1, 12, sort);
        QDiscount qDiscount = QDiscount.discount;
        BooleanBuilder builder = new BooleanBuilder();
        Page<Discount> result = discountService.showAll(pageable, builder);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }

    // 상세 할인 조회 : http://localhost:8080/api/discount/1
    @GetMapping("/discount/{id}")
    public ResponseEntity<Discount> showDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.show(id));
    }

    // 할인 추가 : http://localhost:8080/api/discount
    @PostMapping("/discount")
    public ResponseEntity<Discount> createDiscount(@RequestParam(value = "disDesc", required = true) String disDesc,
                                                   @RequestParam(value = "disPeriod", required = true) String disPeriod,
                                                   @RequestParam(value = "resCode", required = true) Integer resCode
    ){
        Discount vo = new Discount();
        vo.setDisDesc(disDesc);
        vo.setDisPeriod(disPeriod);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(discountService.create(vo));
    }

    // 할인 수정 : http://localhost:8080/api/discount
    @PutMapping("/discount")
    public ResponseEntity<Discount> updateDiscount(@RequestBody Discount vo) {
       Discount result = discountService.update(vo);
       if(result!=null) {
           return ResponseEntity.status(HttpStatus.OK).body(result);
       }
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // 할인 삭제 : http://localhost:8080/api/discount/1
    @DeleteMapping("/discount/{id}")
    public ResponseEntity<Discount> deleteDiscount(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discountService.delete(id));
    }

    // 식당별 할인 보기 : http://localhost:8080/api/discount/1/restaurant
    @GetMapping("/discount/{id}/restaurant")
    public ResponseEntity<List<Discount>> resDiscountList(@PathVariable int id) {
        List<Discount> discountList = discountService.findByResCode(id);
        return ResponseEntity.ok().body(discountList);
    }

}
