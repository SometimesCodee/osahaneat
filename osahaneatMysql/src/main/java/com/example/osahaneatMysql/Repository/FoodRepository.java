package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Integer> {
}
