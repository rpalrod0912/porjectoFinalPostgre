package com.example.porjectofinalpostgre.dto;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.Product;
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
    private String user_id;

    private List<Integer> products;

    public OrdersDTO(Order order){

    }
}
