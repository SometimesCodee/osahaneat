package com.example.osahaneatMysql.Repository;

import com.example.osahaneatMysql.Entity.Keys.KeyOrderItem;
import com.example.osahaneatMysql.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, KeyOrderItem> {
}
