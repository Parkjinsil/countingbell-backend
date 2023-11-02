package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Pick;
import com.kh.countingBell.service.PickService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/pick")
    public ResponseEntity<List<Pick>> showPickList() {

        return ResponseEntity.status(HttpStatus.OK).body(pick.showAll());
    }

    @GetMapping("/pick/{id}")
    public ResponseEntity<Pick> showPick(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(pick.show(id));
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
