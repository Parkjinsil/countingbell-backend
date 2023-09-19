package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Location;
import com.kh.countingBell.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/*")
public class LocationConroller {

    @Autowired
    private LocationService location;

    @GetMapping("/location")
    public ResponseEntity<List<Location>> showAllLocation() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/location/{id}")
    public ResponseEntity<Location> showLocation(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/location")
    public ResponseEntity<Location> createLocation(@RequestBody Location vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.create(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/location")
    public ResponseEntity<Location> updateLocation(@RequestBody Location vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/location/{id}")
    public ResponseEntity<Location> deleteLocation(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
