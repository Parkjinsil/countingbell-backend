package com.kh.countingBell.service;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Restaurant;
import com.kh.countingBell.repo.RestaurantDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RestaurantService {

    @Autowired
    private RestaurantDAO restaurantDAO;


    // 식당 전체 조회
    public List<Restaurant> showAll() {
        return restaurantDAO.findAll();
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


    // 지역에 따른 식당 조회
    public List<Restaurant> findLocalByResCode(int id) {
        return restaurantDAO.findLocalByResCode(id);
    }



    // 음식종류에 따른 식당 조회
    public List<Restaurant> findResByFood(int id) {
        return restaurantDAO.findResByFood(id);
    }


}
