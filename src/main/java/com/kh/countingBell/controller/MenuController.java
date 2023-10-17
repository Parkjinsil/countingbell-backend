package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.MenuService;
import com.kh.countingBell.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class MenuController {

    @Value("${countingbell.upload.path}")
    private String uploadPath;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurant;

    @GetMapping("/menu")
    public ResponseEntity<List<Menu>> showAllMenu() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.showAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> showMenu(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

//    @PostMapping("/menu")
//    public ResponseEntity<Menu> createMenu(@RequestBody Menu vo) {
//        try {
//            return ResponseEntity.status(HttpStatus.OK).body(menu.create(vo));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//    }
//
    @PostMapping("/menu")
    public ResponseEntity<Menu> createMenu(@RequestParam(value = "resCode", required = true) Integer resCode,
                                           @RequestPart(value = "menuPicture", required = true) MultipartFile menuPicture,
                                           @RequestParam(value = "menuName", required = true) String menuName,
                                           @RequestParam(value = "menuPrice", required = true) String menuPrice) {
        log.info("menuPicture : " + menuPicture);
        log.info("menuName : " + menuName);
        log.info("menuPrice : " + menuPrice);
        log.info("resCode : " + resCode);

        String originalPhoto = menuPicture.getOriginalFilename();
        String realImage = originalPhoto.substring(originalPhoto.lastIndexOf("\\")+1);
        String uuid = UUID.randomUUID().toString();
        String savePhoto = uploadPath + File.separator + uuid + "_" + realImage;
        Path pathPhoto = Paths.get(savePhoto);

        try {
            menuPicture.transferTo(pathPhoto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

            Menu vo = new Menu();
            vo.setMenuPicture(uuid + "_" + realImage);
            vo.setMenuName(menuName);
            vo.setMenuPrice(menuPrice);

            Restaurant restaurant = new Restaurant();
            restaurant.setResCode(resCode);
            vo.setRestaurant(restaurant);



        return ResponseEntity.status(HttpStatus.OK).body(menuService.create(vo));

    }




    @PutMapping("/menu")
    public ResponseEntity<Menu> updateMenu(@RequestBody Menu vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }







}
