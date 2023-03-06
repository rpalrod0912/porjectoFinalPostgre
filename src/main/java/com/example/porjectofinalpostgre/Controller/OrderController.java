package com.example.porjectofinalpostgre.Controller;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Service.OrderService;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController  {


    /*ESTRUCTURA PARA ENVIAR JSON DE POST ORDER
    {
		"user_id":1,
	"products":[7],
	"quantity":[1]
}
    */
    @Autowired
    private OrderService service;

    @PostMapping
    public Order createOrder(@RequestBody OrdersDTO order){

        System.out.println(order);
        return service.addOrder(order);
    }


    @GetMapping
    public List<OrdersDTO> getOrders(){
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public OrdersDTO getOrder( @PathVariable("id") String id){
        System.out.println(id);

        return service.getOrderById(id);
    }


    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable("id") String id){
        return service.deleteOrder(id);
    }
}
