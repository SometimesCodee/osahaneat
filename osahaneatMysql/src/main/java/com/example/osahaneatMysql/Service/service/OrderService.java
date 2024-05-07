package com.example.osahaneatMysql.Service.service;

import com.example.osahaneatMysql.Entity.*;
import com.example.osahaneatMysql.Entity.Keys.KeyOrderItem;
import com.example.osahaneatMysql.Repository.OrderItemRepository;
import com.example.osahaneatMysql.Repository.OrderRepository;
import com.example.osahaneatMysql.Service.imp.OrderServiceImp;
import com.example.osahaneatMysql.payload.Request.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements OrderServiceImp {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public boolean addOrder(OrderRequest orderRequest) {
        try {
            Orders order = new Orders();

            Users user = new Users();
            user.setId(orderRequest.getUserId());

            Restaurant restaurant = new Restaurant();
            restaurant.setId(orderRequest.getResId());

            order.setUsers(user);
            order.setRestaurant(restaurant);

            orderRepository.save(order);

            List<OrderItem> orderItemList = new ArrayList<>();
            for (int foodId : orderRequest.getFoodIds()){
                OrderItem orderItem = new OrderItem();

                Food foodOrder = new Food();
                foodOrder.setId(foodId);

                KeyOrderItem keyOrderItem = new KeyOrderItem();
                keyOrderItem.setOrderId(order.getId());
                keyOrderItem.setFoodId(foodId);

                orderItem.setFood(foodOrder);
                orderItem.setOrders(order);
                orderItem.setKeys(keyOrderItem);

                orderItemList.add(orderItem);
            }
            orderItemRepository.saveAll(orderItemList);
            return true;

        }catch (Exception e){
            System.out.println("Error in addOrder" + e.getMessage());
            return false;
        }
    }
}
