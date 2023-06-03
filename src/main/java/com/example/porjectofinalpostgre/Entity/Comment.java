package com.example.porjectofinalpostgre.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(indexes = {
        @Index(name="comments_constraint",columnList = "product_id_id_product, user_id_id_user", unique = true)
})

@Getter
@Setter
@NoArgsConstructor
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 1000)
    private String text;

    @NotNull(message = "Producto requerida")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Product productId;

    @NotNull(message = "Usuario requerido")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User userId;

    @NotNull(message = "Puntuacion requerido")
    private Integer rating;

}
