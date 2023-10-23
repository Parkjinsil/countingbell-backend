package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Location;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.LocationService;
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
public class LocationConroller {

    @Autowired
    private LocationService location;

    @Autowired
    private RestaurantService restaurant;


    // 위치 전체 조회 http://localhost:8080/api/public/location?page=1
    @GetMapping("/public/location")
    public ResponseEntity<List<Location>> showAllLocation(@RequestParam(name="page", defaultValue = "1") int page) {
        // 정렬
        Sort sort = Sort.by("localCode").descending();

        // 한 페이지에 10개
        Pageable pageable = PageRequest.of(page-1, 10, sort);

        Page<Location> result = location.showAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }

    // 위치 1개 조회
    @GetMapping("/public/location/{id}")
    public ResponseEntity<Location> showLocation(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(location.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @PostMapping("/location")
//    public ResponseEntity<Location> createLocation(@RequestParam(value = "localName", required = true) String localName) {
//       log.info("localName : " + localName);
//
//       Location vo = new Location();
//       vo.setLocalName(localName);
//
//       return ResponseEntity.status(HttpStatus.OK).body(location.create(vo));
//    }

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


    // 위치별 식당조회
    @GetMapping("/location/{id}/restaurant")
    public ResponseEntity<List<Restaurant>> findByResCode(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(restaurant.findByLocalCode(id));
    }
}
