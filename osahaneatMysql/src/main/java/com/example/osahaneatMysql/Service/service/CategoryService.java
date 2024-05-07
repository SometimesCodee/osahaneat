package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.DTO.CategoryDTO;
import com.example.osahaneatMysql.DTO.FoodDTO;
import com.example.osahaneatMysql.Entity.Category;
import com.example.osahaneatMysql.Entity.Food;
import com.example.osahaneatMysql.Repository.CategoryRepository;
import com.example.osahaneatMysql.Service.imp.CategoryServiceImp;
import org.hibernate.annotations.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    CategoryRepository categoryRepository;

    @Cacheable("getCategoryHomePage")
    @Override
    public List<CategoryDTO> getCategotyHomePage() {
        System.out.println("get category home page");
        List<CategoryDTO> categories = new ArrayList<>();
        try {
            List<Category> categoriesList = categoryRepository.findAll();
            for (Category category : categoriesList) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setName(category.getNameCate());

                List<FoodDTO> foodDTOList = new ArrayList<>();
                for (Food food : category.getListFood()){
                    FoodDTO foodDTO = new FoodDTO();
                    foodDTO.setTitle(food.getTitle());
                    foodDTO.setPrice(food.getPrice());
                    foodDTO.setImage(food.getImage());
                    foodDTO.setFreeship(food.isFreeship());
                    foodDTO.setTimeShip(food.getTimeShip());
                    foodDTOList.add(foodDTO);
                }
                categoryDTO.setFoods(foodDTOList);
                categories.add(categoryDTO);
            }
        }catch (Exception e){
            System.out.println("Error while getting categories" + e.getMessage());
        }
        return categories;
    }
}
