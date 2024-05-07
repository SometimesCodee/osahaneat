package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
