package com.kh.countingBell.service;


import com.kh.countingBell.domain.Location;
import com.kh.countingBell.repo.LocationDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LocationService {

    @Autowired
    private LocationDAO locationDAO;



    public Page<Location> showAll(Pageable pageable) {

        return locationDAO.findAll(pageable);
    }

    public Location show(int id) {
        return locationDAO.findById(id).orElse(null);
    }

    public Location create(Location location) {
        return locationDAO.save(location);
    }

    public Location update(Location location) {
        Location local = locationDAO.findById(location.getLocalCode()).orElse(null);
        if (local != null) {
            return locationDAO.save(location);
        }
        return null;
    }

    public Location delete(int id) {
        Location target = locationDAO.findById(id).orElse(null);
        locationDAO.delete(target);
        return target;
    }


}
