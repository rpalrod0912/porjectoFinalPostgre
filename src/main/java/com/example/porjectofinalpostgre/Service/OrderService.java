package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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

        Order newOrder = new Order();

        System.out.println(order.getUser_id());
        String userId=order.getUser_id();
        System.out.println(userRepository.findById(userId).get());
        System.out.println("hola");
        newOrder.setUser_id(userRepository.findById(userId).get());
        for (Integer product_id : order.getProducts()) {
            System.out.println(product_id);
            newOrder.getProducts().add(productRepository.findById(product_id).get());
        }
        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders(){

        List <Order> listaOrder = orderRepository.findAll();
        System.out.println(orderRepository.findAll());
        return orderRepository.findAll();
    }

    public Order getOrderById(String orderId){
        return orderRepository.findById(orderId).get();
    }
    public String deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
        return orderId+" pedido eliminadio de la API";
    }


}
