package com.example.porjectofinalpostgre.Controller;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping
    @ResponseStatus
    public Order createOrder(@RequestBody Order order){
        return service.addOrder(order);
    }


    @GetMapping
    public List<Order> getOrders(){
        return service.getAllOrders();
    }

    @DeleteMapping("/{id}")
    public String deleteOrdeer(@PathVariable String orderId){
        return service.deleteOrder(orderId);
    }
}
