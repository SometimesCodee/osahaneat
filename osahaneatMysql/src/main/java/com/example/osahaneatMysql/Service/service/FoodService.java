package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.FoodDTO;
import com.example.osahaneatMysql.DTO.RestaurantDTO;
import com.example.osahaneatMysql.Entity.Category;
import com.example.osahaneatMysql.Entity.Food;
import com.example.osahaneatMysql.Entity.Restaurant;
import com.example.osahaneatMysql.Repository.FoodRepository;
import com.example.osahaneatMysql.Service.imp.FileServiceImp;
import com.example.osahaneatMysql.Service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class FoodService implements FoodServiceImp {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public boolean insertFood(MultipartFile file, String title, boolean isFreeShip, String timeShip, double price, int cateId, String desc) {
        boolean success = false;
        try {
            boolean isSuccess = fileServiceImp.save(file);
            if(isSuccess) {
                Food food = new Food();
                food.setTitle(title);
                food.setFreeship(isFreeShip);
                food.setImage(file.getOriginalFilename());
                food.setPrice(price);
                food.setTimeShip(timeShip);
                food.setDesc(desc);

                Category category = new Category();
                category.setId(cateId);
                food.setCategory(category);

                foodRepository.save(food);
                success = true;
            }
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return success;
    }

    @Cacheable("getAllFood")
    @Override
    public List<FoodDTO> getAllFood() {
        List<FoodDTO> foodDTOList = new ArrayList<>();
        try {
//            PageRequest pageRequest = PageRequest.of(0, 6);
//            Page<Restaurant> list = restaurantRepository.findAll(pageRequest);
            List<Food> list = foodRepository.findAll();

            for (Food food : list) {
                FoodDTO foodDTO = new FoodDTO();
                foodDTO.setTitle(food.getTitle());
                foodDTO.setFreeship(food.isFreeship());
                foodDTO.setImage(food.getImage());
                foodDTO.setPrice(food.getPrice());
                foodDTO.setTimeShip(food.getTimeShip());
                foodDTO.setDesc(food.getDesc());
                foodDTOList.add(foodDTO);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return foodDTOList;
    }
}
