package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public String deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
        return orderId+" pedido eliminadio de la API";
    }


}
