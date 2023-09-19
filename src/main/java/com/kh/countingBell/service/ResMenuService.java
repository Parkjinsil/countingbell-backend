package com.kh.countingBell.service;

import com.kh.countingBell.domain.Menu;
import com.kh.countingBell.domain.ResMenu;
import com.kh.countingBell.repo.MenuDAO;
import com.kh.countingBell.repo.ResMenuDAO;
import com.kh.countingBell.repo.RestaurantDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResMenuService {

    @Autowired
    private ResMenuDAO resMenuDAO;

    // 식당 1개에 따른 메뉴 조회
    public List<Menu> findByResCode(int id) {
        return resMenuDAO.findByResCode(id);
    }
}
