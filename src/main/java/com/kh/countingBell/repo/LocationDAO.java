package com.kh.countingBell.repo;

import com.kh.countingBell.domain.Location;
import com.kh.countingBell.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationDAO extends JpaRepository<Location, Integer>, QuerydslPredicateExecutor<Location> {



}
