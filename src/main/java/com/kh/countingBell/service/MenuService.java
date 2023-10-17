package com.kh.countingBell.service;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.repo.MenuDAO;
import com.kh.countingBell.repo.RestaurantDAO;
import com.querydsl.core.BooleanBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    @Autowired
    private RestaurantDAO restaurantDAO;

    public Page<Menu> showAll(Pageable pageable) {

        return menuDAO.findAll(pageable);
    }

    public Menu show(int id) {
        return menuDAO.findById(id).orElse(null);
    }


    public Menu create(Menu menu) {
        return menuDAO.save(menu);
    }

    public Menu update(Menu menu) {
        Menu target = menuDAO.findById(menu.getMenuCode()).orElse(null);
        if(target != null) {
            return menuDAO.save(menu);
        }
        return null;
    }

    public Menu delete(int id) {
        Menu target = menuDAO.findById(id).orElse(null);
        menuDAO.delete(target);
        return target;
    }


    // 식당 1개의 메뉴 조회
    public List<Menu>  findByResCode(int id) {
        return menuDAO.findByResCode(id);
    }




}
