package com.kh.countingBell.service;

import com.kh.countingBell.domain.Location;
import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.repo.MenuDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuService {

    @Autowired
    private MenuDAO menuDAO;

    public List<Menu> showAll() {
        return menuDAO.findAll();
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

    public List<Menu> findByResCode(int code) {
        return menuDAO.findByResCode(code);
    }


}
