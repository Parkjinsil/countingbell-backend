package com.kh.countingBell.service;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.repo.FoodDAO;
import com.kh.countingBell.repo.LocationDAO;
import com.kh.countingBell.repo.MenuDAO;
import com.kh.countingBell.repo.RestaurantDAO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Autowired
    private FoodDAO foodDAO;

    @Autowired
    private MenuDAO menuDAO;

    @Autowired
    private LocationDAO locationDAO;


    // 아이디별 식당 전체조회
    public List<Restaurant> getResByUserId(String userId) {
        return restaurantDAO.getResByUserId(userId);
    }


    // 식당 전체 조회
    public Page<Restaurant> showAll(Pageable pageable) {
        return restaurantDAO.findAll(pageable);
    }

    // 식당 상세 조회
    public Restaurant show(int id) {
        return restaurantDAO.findById(id).orElse(null);

    }

    // 식당명으로 식당 검색
    public List<Restaurant> searchResByName(String keyword) {
      return restaurantDAO.searchResByName(keyword);

    }





    // 식당 추가
    public Restaurant create(Restaurant restaurant) {
                return restaurantDAO.save(restaurant);
    }

    // 식당 수정
    public Restaurant update(Restaurant restaurant) {
        Restaurant target = restaurantDAO.findById(restaurant.getResCode()).orElse(null);
        if(target != null) {
            return restaurantDAO.save(restaurant);
        }
        return null;
    }

    // 식당 삭제
    public Restaurant delete(int id) {
        Restaurant target = restaurantDAO.findById(id).orElse(null);
        restaurantDAO.delete(target);
        return target;
    }





    // 음식종류에 따른 식당 조회
    public List<Restaurant> findResByFood(int id) {
        return restaurantDAO.findResByFood(id);
    }



    // 지역에 따른 식당 조회
    @Transactional
    public List<Restaurant> findByLocalCode(int id) {

        return restaurantDAO.findByLocalCode(id);
    }

    // 음식+종류에 따른 식당 조회 ( 빠른예약 )
    public List<Restaurant> findResByFilter(int foodCode, int localCode){
        return restaurantDAO.findResByFilter(foodCode, localCode);
    }

}
