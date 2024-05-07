package com.example.osahaneatMysql.Entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "res_id")
    private Restaurant restaurant;

    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "orders")
    private Set<OrderItem> listOrderItem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Set<OrderItem> getListOrderItem() {
        return listOrderItem;
    }

    public void setListOrderItem(Set<OrderItem> listOrderItem) {
        this.listOrderItem = listOrderItem;
    }
}
