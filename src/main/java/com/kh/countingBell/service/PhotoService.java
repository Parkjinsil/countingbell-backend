package com.kh.countingBell.service;

import com.kh.countingBell.domain.Photo;
import com.kh.countingBell.repo.PhotoDAO;
import com.kh.countingBell.repo.RestaurantDAO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PhotoService {

    @Autowired
    private PhotoDAO photoDAO;

    @Autowired
    private RestaurantDAO restaurantDAO;

    public List<Photo> showAll() {
        return photoDAO.findAll();
    }

    public Photo show(int id) {
        Photo photo = photoDAO.findById(id).orElse(null);
//        Restaurant restaurant = restaurantDAO.findById(photo.getRestaurant().getId()).orElse(null);
//        photo.setRestaurant(restaurant);
        return photo;
    }

    public Photo create(Photo photo) {
        return photoDAO.save(photo);
    }

    public Photo update(Photo photo) {
        Photo target = photoDAO.findById(photo.getResPhotoCode()).orElse(null);
        if(target!=null){
            return photoDAO.save(photo);
        }
        return null;
    }

    public Photo delete(int id) {
        Photo target = photoDAO.findById(id).orElse(null);
        photoDAO.delete(target);
        return target;
    }

    // 특정 식당의 모든 사진 조회
    public List<Photo> findByResCode(int id) {
        return photoDAO.findByResCode(id);
    }

}













