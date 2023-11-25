package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Photo;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.PhotoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/*")
@CrossOrigin(origins = {"*"}, maxAge = 6000)
public class PhotoController {

    @Value("${countingbell.upload.path}")
    private String uploadPath;

    @Autowired
    private PhotoService photoService;

    @GetMapping("/photo")
    public ResponseEntity<List<Photo>> showPhotoList(){
        return ResponseEntity.status(HttpStatus.OK).body(photoService.showAll());
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Photo> showPhoto(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.show(id));
    }

    @PostMapping("/photo")
    public ResponseEntity<Photo> createPhoto(Integer resCode,
                                             String photoName,
                                             @RequestPart(value = "resPhoto", required = true) MultipartFile resPhoto){

        String originalPhoto = resPhoto.getOriginalFilename();
        String realImage = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);
        String uuid = UUID.randomUUID().toString();
        String savePhoto = uploadPath + File.separator + uuid + "_" + realImage;
        Path pathPhoto = Paths.get(savePhoto);

        try {
            resPhoto.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Photo vo = new Photo();
        vo.setResPhoto(uuid + "_" + realImage);
        vo.setPhotoName(photoName);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(photoService.create(vo));
    }

    @PutMapping("/photo")
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo vo) {
        Photo result = photoService.update(vo);
        if(result!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/photo/{id}")
    public ResponseEntity<Photo> deletePhoto(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photoService.delete(id));
    }

}



























