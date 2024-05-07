package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.CategoryDTO;
import com.example.osahaneatMysql.DTO.FoodDTO;
import com.example.osahaneatMysql.DTO.RestaurantDTO;
import com.example.osahaneatMysql.Entity.Food;
import com.example.osahaneatMysql.Entity.MenuRestaurant;
import com.example.osahaneatMysql.Entity.RatingRestaurant;
import com.example.osahaneatMysql.Entity.Restaurant;
import com.example.osahaneatMysql.Repository.RestaurantRepository;
import com.example.osahaneatMysql.Service.imp.FileServiceImp;
import com.example.osahaneatMysql.Service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.domain.Pageable;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    FileServiceImp fileServiceImp;

    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeShip, String address, String open_date) {
        boolean sucess = false;
        try {
            boolean isSuccess = fileServiceImp.save(file);
            if(isSuccess) {
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setAddress(address);
                restaurant.setFreeship(isFreeShip);
                restaurant.setImage(file.getOriginalFilename());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date openDate = simpleDateFormat.parse(open_date);
                restaurant.setOpenDate(openDate);
                restaurantRepository.save(restaurant);
                sucess = true;
            }
        }catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }

        return sucess;
    }

    @Cacheable("getAllRestaurant")
    @Override
    public List<RestaurantDTO> getAllRestaurants() {
        List<RestaurantDTO> restaurantDTOList = new ArrayList<>();
        try {
//            PageRequest pageRequest = PageRequest.of(0, 6);
//            Page<Restaurant> list = restaurantRepository.findAll(pageRequest);
            List<Restaurant> list = restaurantRepository.findAll();

            for (Restaurant restaurant : list) {
                RestaurantDTO restaurantDTO = new RestaurantDTO();
                restaurantDTO.setId(restaurant.getId());
                restaurantDTO.setTitle(restaurant.getTitle());
                restaurantDTO.setSubtitle(restaurant.getSubtitle());
                restaurantDTO.setDescription(restaurant.getDescription());
                restaurantDTO.setAddress(restaurant.getAddress());
                restaurantDTO.setOpenDate(restaurant.getOpenDate());
                restaurantDTO.setFreeship(restaurant.isFreeship());
                restaurantDTO.setImage(restaurant.getImage());
                restaurantDTO.setRating(calculateRating(restaurant.getListRatingRestaurant()));
                restaurantDTOList.add(restaurantDTO);
            }
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
        }
        return restaurantDTOList;
    }

    @Override
    public RestaurantDTO getRestaurantById(int id) {
        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        if(restaurant.isPresent()) {
            restaurantDTO.setId(restaurant.get().getId());
            restaurantDTO.setTitle(restaurant.get().getTitle());
            restaurantDTO.setSubtitle(restaurant.get().getSubtitle());
            restaurantDTO.setDescription(restaurant.get().getDescription());
            restaurantDTO.setAddress(restaurant.get().getAddress());
            restaurantDTO.setOpenDate(restaurant.get().getOpenDate());
            restaurantDTO.setFreeship(restaurant.get().isFreeship());
            restaurantDTO.setImage(restaurant.get().getImage());
            restaurantDTO.setRating(calculateRating(restaurant.get().getListRatingRestaurant()));

            List<CategoryDTO> categoryDTOList = new ArrayList<>();

            for (MenuRestaurant menuRestaurant : restaurant.get().getListMenuRestaurant()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(menuRestaurant.getCategory().getNameCate());

                List<FoodDTO> foodDTOList = new ArrayList<>();

                for (Food food : menuRestaurant.getCategory().getListFood()){
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setId(food.getId());
                    foodDTO.setCateId(food.getCategory().getId());
                    foodDTO.setTitle(food.getTitle());
                    foodDTO.setPrice(food.getPrice());
                    foodDTO.setImage(food.getImage());
                    foodDTO.setFreeship(food.isFreeship());
                    foodDTO.setTimeShip(food.getTimeShip());
                    foodDTO.setDesc(food.getDesc());

                    foodDTOList.add(foodDTO);
                }
                categoryDTO.setFoods(foodDTOList);
                categoryDTOList.add(categoryDTO);
            }
            restaurantDTO.setCategory(categoryDTOList);

        }
        return restaurantDTO;
    }

    private double calculateRating(Set<RatingRestaurant> listRating){
        double point = 0;
        for (RatingRestaurant data : listRating) {
            point += data.getRatePoint();
        }
        return point/listRating.size();
    }
}
