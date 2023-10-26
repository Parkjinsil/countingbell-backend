package com.kh.countingBell.controller;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.service.*;
import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.domain.Photo;
import com.kh.countingBell.domain.Reservation;
import com.kh.countingBell.service.DiscountService;
import com.kh.countingBell.service.PhotoService;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.service.ReservationService;
import com.kh.countingBell.service.RestaurantService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.log4j.Log4j2;
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

@RestController
@RequestMapping("/api/*")
@Log4j2
@CrossOrigin(origins={"*"}, maxAge = 6000)
public class RestaurantController {

    @Value("${countingbell.upload.path}")
    private String uploadPath;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DiscountService discount;

    @Autowired
    private PhotoService photo;

    @Autowired
    private ResCommentService comment;

    @Autowired
    private MenuService menuService;

    @Autowired
    private PickService pick;

    @Autowired
    private MenuService menuService;

    // 식당 1개에 따른 찜 조회
    @GetMapping("/restaurant/{id}/pick")
    public ResponseEntity<List<Pick>> resPickList(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(pick.findByResCode(id));
    }

    // 식당 1개에 따른 할인 조회
    // http://localhost:8080/restaurant/1/discount
    @GetMapping("/restaurant/{id}/discount")
    public ResponseEntity<List<Discount>> resDiscountList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(discount.findByResCode(id));
    }

    // 식당 1개에 따른 식당사진 조회
    // http://localhost:8080/restaurant/1/photo
    @GetMapping("/restaurant/{id}/photo")
    public ResponseEntity<List<Photo>> resPhotoList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(photo.findByResCode(id));
    }

    // 음식종류에 따른 식당 조회 : http://localhost:8080/api/restaurant/1/food
    @GetMapping("/restaurant/{id}/food")
    public ResponseEntity<List<Restaurant>> findResByFood(@PathVariable int id) {
        log.info("LocationController 음식타입별 식당 찾기 실행");
        List<Restaurant> resList = restaurantService.findResByFood(id);
        log.info("restaurantList : " + resList);

        return ResponseEntity.ok().body(resList);

    // 식당 1개의 메뉴 조회
    @GetMapping("/restaurant/{id}/menu")
    public ResponseEntity<List<Menu>> resMenuList(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(menuService.findByResCode(id));
    }







    // 식당 전체 조회 : GET =  http://localhost:8080/api/public/restaurant?page=1
    @GetMapping("/public/restaurant")
    public ResponseEntity<List<Restaurant>> restaurantList(@RequestParam(name="page", defaultValue = "1") int page, @RequestParam(name="food", required = false) Integer food) {


        // 정렬
        Sort sort = Sort.by("resCode").descending();

        // 한 페이지에 10개
        Pageable pageable = PageRequest.of(page-1, 10, sort);

        Page<Restaurant> result = restaurantService.showAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(result.getContent());


    }


// 식당 1개 보기
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> showRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.show(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestParam(value="resName", required = true)String resName,
                                                       @RequestParam(value="resAddr", required = true)String resAddr,
                                                       @RequestParam(value="resPhone", required = true)String resPhone,
                                                       @RequestParam(value="resOpenHour", required = true)String resOpenHour,
                                                       @RequestParam(value="resClose", required = true)String resClose,
                                                       @RequestParam(value="resDesc", required = true)String resDesc,
                                                       @RequestParam(value="localCode", required = true) Integer localCode,
                                                       @RequestParam(value = "foodCode", required = true) Integer foodCode,
                                                       @RequestParam(value = "id", required = true) String id,
                                                       @RequestParam(value="resPicks", required = true)Integer resPicks,
                                                       @RequestPart(value = "resPicture", required = true) MultipartFile resPicture) {


        log.info("들어옴?");
        log.info(resPicture.getOriginalFilename());

        String originalPicture = resPicture.getOriginalFilename();
        String realImage = originalPicture.substring(originalPicture.lastIndexOf("\\")+1);
        String uuid = UUID.randomUUID().toString();
        String savePicture = uploadPath + File.separator + uuid + "_" + realImage;
        Path pathPicture = Paths.get(savePicture);

        try {
            resPicture.transferTo(pathPicture);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Restaurant res = new Restaurant();
        res.setResName(resName);
        res.setResAddr(resAddr);
        res.setResPhone(resPhone);
        res.setResOpenHour(resOpenHour);
        res.setResClose(resClose);
        res.setResDesc(resDesc);
        res.setResPicks(resPicks);
        res.setResPicture(uuid + "_" + realImage);

        Location loc = new Location();
        loc.setLocalCode(localCode);
        res.setLocation(loc);

        Food food = new Food();
        food.setFoodCode(foodCode);
        res.setFood(food);

        Member mem = new Member();
        mem.setId(id);
        res.setMember(mem);

        log.info("setResName : " + resName);
        log.info("setResAddr : " + resAddr);
        log.info("setResPhone : " + resPhone);
        log.info("setResOpenHour : " + resOpenHour);
        log.info("setResClose : " + resClose);
        log.info("setResDesc : " + resDesc);
        log.info("setResPicks : " + resPicks);
        log.info("setResPicture : " + resPicture);

        return ResponseEntity.ok().body(restaurantService.create(res));

    }

    
    // 식당 수정하기
    @PutMapping("/restaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody Restaurant vo) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.update(vo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    // 식당 삭제
    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable int id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(restaurantService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    //식당 1개에 따른 예약 전체 조회 : GET - http://localhost:8080/api/restaurant/1/reservation
    @GetMapping("/restaurant/{id}/reservation")
    public ResponseEntity<List<Reservation>> ReservationList(@PathVariable int id) {
        log.info("id : " + id);
        return ResponseEntity.status(HttpStatus.OK).body(reservationService.findByResCode(id));
    }



    //식당 찜 추가 POST - http://localhost:8080/api/restaurant/pick
    //중복 처리
//    @PostMapping("/restaurant/pick")
//    public ResponseEntity<RestaurantPick> createRestaurantPick(@RequestBody RestaurantPick restaurantPick) {
//
//        RestaurantPick target = commonLikeService.duplicatedLike(commonLike.getMember().getId(), commonLike.getCommunity().getCommonCode());
//        if (target == null) {
//            commonService.increaseCommonLikes(commonLike.getCommunity().getCommonCode());
//            return ResponseEntity.status(HttpStatus.OK).body(commonLikeService.create(commonLike));
//        } else {
//
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//        }
//
//    }

}
