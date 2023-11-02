package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Pick;
import com.kh.countingBell.domain.QPick;
import com.kh.countingBell.domain.QRestaurant;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.PickService;
import com.querydsl.core.BooleanBuilder;
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

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class PickController {

    @Autowired
    private PickService pick;

    @GetMapping("/public/pick")
    public ResponseEntity<List<Pick>> showPickList(
            @RequestParam(name = "page", defaultValue = "1") int page
    ) {

        // 정렬
        Sort sort = Sort.by("pickCode").descending();

        // 한 페이지에 10개
        Pageable pageable = PageRequest.of(page - 1, 12, sort);

        QPick qPick = QPick.pick;

        BooleanBuilder builder = new BooleanBuilder();

        Page<Pick> result = pick.showAll(pageable, builder);

        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }

    @GetMapping("/pick/{id}")
    public ResponseEntity<Pick> showPick(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(pick.show(id));
    }

    @GetMapping("/pick/member/{id}")
    public ResponseEntity<List<Pick>> showPickByMember(@PathVariable String id) {
        List<Pick> list = pick.findPickById(id);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @PostMapping("/pick")
    public ResponseEntity<Pick> createPick(@RequestBody Pick vo) {
        return ResponseEntity.status(HttpStatus.OK).body(pick.create(vo));
    }

    @PutMapping("/pick")
    public ResponseEntity<Pick> updatePick(@RequestBody Pick vo) {
        Pick result = pick.update(vo);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/pick/{id}")
    public ResponseEntity<Pick> deletePick(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(pick.delete(id));
    }



}
