package com.kh.countingBell.service;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Location;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.repo.FoodDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FoodService {

    @Autowired
    private FoodDAO foodDAO;

    public List<Food> showAll() {
        return foodDAO.findAll();
    }

    public Food show(int id) {
        return foodDAO.findById(id).orElse(null);
    }

    public Food create(Food food) {
        return foodDAO.save(food);
    }

    public Food update(Food food) {
        Food fd = foodDAO.findById(food.getFoodCode()).orElse(null);
        if(fd != null) {
            return foodDAO.save(food);
        }
        return null;
    }

    public Food delete(int id) {
        Food target = foodDAO.findById(id).orElse(null);
        foodDAO.delete(target);
        return target;
    }


}
