package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository  extends JpaRepository<Order, String> {
}