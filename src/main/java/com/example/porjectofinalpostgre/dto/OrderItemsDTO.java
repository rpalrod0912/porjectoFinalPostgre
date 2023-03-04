package com.example.porjectofinalpostgre.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class OrderItemsDTO {

    private @NotNull double price;
    private  @NotNull int quantity;

    private @NotNull int orderId;
    private @NotNull int productId;

    public OrderItemsDTO(){}

    public OrderItemsDTO(@NotNull double price, @NotNull int quantity, @NotNull int orderId, @NotNull int productId){
        this.price = price;
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
