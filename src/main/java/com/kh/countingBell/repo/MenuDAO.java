package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MenuDAO extends JpaRepository<Menu, Integer> {


}
