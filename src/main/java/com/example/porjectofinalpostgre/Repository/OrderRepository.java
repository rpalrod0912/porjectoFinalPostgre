package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Order, String> {

    List<Order> deleteByUserid(User userId);

    List<Order> getByUserid(User userId);

}