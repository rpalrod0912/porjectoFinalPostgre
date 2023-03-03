package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public Order addOrder(OrdersDTO order) {
        System.out.println(order);

        Order newOrder = new Order();
        newOrder.setUser_id(userRepository.findById(order.getUser_id()).get());
        for (Integer product_id : order.getProducts()) {
            newOrder.getProducts().add(productRepository.findById(product_id).get());
        }
        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public String deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
        return orderId+" pedido eliminadio de la API";
    }


}
