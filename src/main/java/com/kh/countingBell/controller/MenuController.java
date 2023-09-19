package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.service.MenuService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class MenuController {

    @Autowired
    private MenuService menu;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> showAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menu.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> show(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menu.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> create(@RequestBody Menu vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menu.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/menu")
    public ResponseEntity<Menu> update(@RequestBody Menu vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menu.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Menu> delete(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menu.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/menu/{id}/restaurant")
    public ResponseEntity<List<Menu>> findByResCode(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(menu.findByResCode(id));
    }
}