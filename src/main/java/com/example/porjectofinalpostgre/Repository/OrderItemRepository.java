package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.OrderItem;
import com.example.porjectofinalpostgre.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem,String> {

    List<OrderItem> getAllById(String id);

    List<OrderItem> getAllByOrderId(String orderId);

}
