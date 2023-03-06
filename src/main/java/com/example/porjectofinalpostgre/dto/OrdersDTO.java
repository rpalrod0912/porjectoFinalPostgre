package com.example.porjectofinalpostgre.dto;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.OrderItem;
import com.example.porjectofinalpostgre.Entity.Product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTO implements Serializable {

    /*ESTRUCTURA PARA ENVIAR JSON DE POST ORDER
 {
     "user_id":1,
 "products":[7],
 "quantity":[1]
}
 */
    private Integer id;
    private String User_id;

    private List<Integer> products;

    private List<Integer> quantity;

    private List<Double> prices;

    private List<OrderItem> orderproducts;
    private Double totalPrice;
    public OrdersDTO(Order order){

    }

/*
    public OrdersDTO(Order order){




    private Integer id;
    private @NotNull Integer userId;

    public OrdersDTO(Order order){
        this.setId(order.getId());
        this.setUserId(order.getUserId());
    }

     */
}
