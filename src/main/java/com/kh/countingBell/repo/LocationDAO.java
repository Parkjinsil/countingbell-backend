package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LocationDAO extends JpaRepository<Location, Integer> {

}
