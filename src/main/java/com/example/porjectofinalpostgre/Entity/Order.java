package com.example.porjectofinalpostgre.Entity;

import com.example.porjectofinalpostgre.dto.OrdersDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class  Order implements Serializable {


    /*ESTRUCTURA PARA ENVIAR JSON DE POST ORDER
 {
     "user_id":1,
 "products":[7],
 "quantity":[1]
}
 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;




    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "idUser", nullable = false)
    private User userid;



    @JsonBackReference
    @ManyToMany
    @JoinTable(
            name = "order_product",
            joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "idProduct")
    )
    private Set<Product> products = new LinkedHashSet<>();

    @ElementCollection
    @CollectionTable(name = "cantidaProd", joinColumns = @JoinColumn(name =
            "id"))
    private List<Integer> Quantity;

    @ElementCollection
    @CollectionTable(name = "preciosProd", joinColumns = @JoinColumn(name =
            "id"))
    private List<Double> prices;



    @Column(name = "preciototal")
    private Double precioFinal;


/*
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name="order_id",referencedColumnName = "id",insertable = false,updatable = false)
    private List<OrderItem> orderItems;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id",referencedColumnName = "id",insertable = false,updatable = false)
    private User user;

    public Order(PlaceOrderDto orderDto, int userId){
        this.userId=userId;
        this.totalPrice=orderDto.getTotalPrice();
    }
    */

}
