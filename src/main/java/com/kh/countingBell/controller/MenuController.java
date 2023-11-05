package com.kh.countingBell.controller;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.MenuService;
import com.kh.countingBell.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class MenuController {

    @Value("${countingbell.upload.path}")
    private String uploadPath;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RestaurantService restaurant;



    // 식당별 메뉴 찾기 : http://localhost:8080/api/menu/{resCode}/restaurant
    @GetMapping("/menu/{id}/restaurant")
    public ResponseEntity<List<Menu>> resMenuList(@PathVariable int id) {
        List<Menu> menuList = menuService.findByResCode(id);
        return ResponseEntity.ok().body(menuList);
    }

    // 음식명으로 식당 검색하기
    @GetMapping("/search/{keyword}")
    public List<Menu> searchResByMenuName(@PathVariable String keyword) {
        log.info("keyword : " + keyword);
        return menuService.searchResByMenuName(keyword);
    }



    // 메뉴 전체 조회 : GET - http://localhost:8080/api/menu
    @GetMapping("/public/menu")
    public ResponseEntity<List<Menu>> showAllMenu(@RequestParam(name="page", defaultValue = "1") int page) {
        // 정렬
        Sort sort = Sort.by("menuCode").descending();

        // 한 페이지에 10개
        Pageable pageable = PageRequest.of(page-1, 10, sort);

        Page<Menu> result = menuService.showAll(pageable);
//        log.info("Total Pages : " + result.getTotalPages()); // 총 몇 페이지
//        log.info("Total Count : " + result.getTotalElements()); // 전체 개수
//        log.info("Page Number : " + result.getNumber()); // 현재 페이지 번호
//        log.info("Page Size : " + result.getSize()); // 페이지당 데이터 개수
//        log.info("Next Page : " + result.hasNext()); // 다음 페이지가 있는지 존재 여부
//        log.info("First Page : " + result.isFirst()); // 시작 페이지 여부


        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
    }


    // 메뉴 1개 조회 : GET - http://localhost:8080/api/menu/1
    @GetMapping("/public/menu/{id}")
    public ResponseEntity<Menu> showMenu(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 메뉴 등록
    @PostMapping("/menu")
    public ResponseEntity<Menu> createMenu(Integer resCode,
                                           @RequestPart(value = "menuPicture", required = true) MultipartFile menuPicture,
                                           String menuName,
                                           String menuDesc,
                                           String menuPrice) {

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
        vo.setMenuDesc(menuDesc);
        vo.setMenuPrice(menuPrice);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

        return ResponseEntity.status(HttpStatus.OK).body(menuService.create(vo));

    }



    // 메뉴 수정
    @PutMapping("/menu")
    public ResponseEntity<Menu> updateMenu(@RequestParam(value = "menuCode", required = true) Integer menuCode,
                                           @RequestParam(value = "resCode", required = true) Integer resCode,
                                           @RequestPart(value = "menuPicture", required = true) MultipartFile menuPicture,
                                           @RequestParam(value = "menuName", required = true) String menuName,
                                           @RequestParam(value = "menuDesc", required = true) String menuDesc,
                                           @RequestParam(value = "menuPrice", required = true) String menuPrice) {

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
        vo.setMenuCode(menuCode);
        vo.setMenuPicture(uuid + "_" + realImage);
        vo.setMenuName(menuName);
        vo.setMenuDesc(menuDesc);
        vo.setMenuPrice(menuPrice);

        Restaurant restaurant = new Restaurant();
        restaurant.setResCode(resCode);
        vo.setRestaurant(restaurant);

 return ResponseEntity.status(HttpStatus.OK).body(menuService.create(vo));
    }

    //    http://localhost:8080/api/menu/{id}
    // 메뉴 삭제
    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(menuService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}