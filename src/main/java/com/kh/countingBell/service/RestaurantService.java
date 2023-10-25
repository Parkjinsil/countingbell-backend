package com.kh.countingBell.service;

import com.kh.countingBell.domain.*;
import com.kh.countingBell.repo.FoodDAO;
import com.kh.countingBell.repo.LocationDAO;
import com.kh.countingBell.repo.MenuDAO;
import com.kh.countingBell.repo.RestaurantDAO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

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



    // 식당 전체 조회
    public Page<Restaurant> showAll(Pageable pageable) {
        return restaurantDAO.findAll(pageable);
    }

    // 식당 상세 조회
    public Restaurant show(int id) {
        return restaurantDAO.findById(id).orElse(null);

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



}
