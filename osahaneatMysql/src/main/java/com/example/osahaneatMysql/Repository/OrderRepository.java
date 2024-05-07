package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
