

package com.example.porjectofinalpostgre.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter@Setter
@Entity
@Table(name="orderitems")
public class OrderItem  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @Column(name="productId")
    private @NotNull Integer idProduct;

    @Column(name="quantity")
    private @NotNull int quantity;
    @Column(name = "price")
    private @NotNull double price;
    @Column(name = "order_id")
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;

    @OneToOne
    @JoinColumn(name = "productId",referencedColumnName = "idProduct",insertable = false,updatable = false)
    private Product product;

    public OrderItem(){}


    public OrderItem(Integer orderId, @NotNull Integer product_id, @NotNull Integer quantity, @NotNull double price) {
        this.idProduct = product_id;
        this.quantity = quantity;
        this.price = price;
        this.orderId=orderId;
    }


}
