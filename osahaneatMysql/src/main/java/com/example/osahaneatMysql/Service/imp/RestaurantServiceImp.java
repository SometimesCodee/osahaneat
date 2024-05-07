package com.example.osahaneatMysql.Service.imp;

import com.example.osahaneatMysql.DTO.RestaurantDTO;
import com.example.osahaneatMysql.Entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeShip, String address, String open_date);
    List<RestaurantDTO> getAllRestaurants();
    RestaurantDTO getRestaurantById(int id);
}
