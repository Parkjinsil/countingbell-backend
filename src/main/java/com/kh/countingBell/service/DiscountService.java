package com.kh.countingBell.service;

import com.kh.countingBell.domain.Discount;
import com.kh.countingBell.repo.DiscountDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DiscountService {

    @Autowired
    private DiscountDAO discountDAO;

//    @Autowired
//    private RestaurantDAO restaurantDAO;

    public List<Discount> showAll() {
        return discountDAO.findAll();
    }

    public Discount show(int id) {
        Discount discount = discountDAO.findById(id).orElse(null);
//        Restaurant restaurant = restaurantDAO.findById(discount.getRestaurant().getId()).orElse(null);
//        discount.setRestaurant(restaurant);
        return discount;
    }

    public Discount create(Discount discount) {
        return discountDAO.save(discount);
    }

    public Discount update(Discount discount) {
        Discount target = discountDAO.findById(discount.getDisCode()).orElse(null);
        if(target!=null){
            return discountDAO.save(discount);
        }
        return null;
    }

    public Discount delete(int id){
        Discount target = discountDAO.findById(id).orElse(null);
        discountDAO.delete(target);
        return target;
    }

    public List<Discount> findByResCode(int id) {
        return discountDAO.findByResCode(id);
    }

}
