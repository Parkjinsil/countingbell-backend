package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Food;
import com.kh.countingBell.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodDAO extends JpaRepository<Food, Integer> {




}
