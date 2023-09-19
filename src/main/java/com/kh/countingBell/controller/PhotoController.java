package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Photo;
import com.kh.countingBell.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/*")
public class PhotoController {

    @Autowired
    private PhotoService photo;

    @GetMapping("/photo")
    public ResponseEntity<List<Photo>> showPhotoList(){
        return ResponseEntity.status(HttpStatus.OK).body(photo.showAll());
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Photo> showPhoto(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photo.show(id));
    }

    @PostMapping("/photo")
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo vo) {
        return ResponseEntity.status(HttpStatus.OK).body(photo.create(vo));
    }

    @PutMapping("/photo")
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo vo) {
        Photo result = photo.update(vo);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photo.delete(id));
    }

}



























