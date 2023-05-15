package com.example.porjectofinalpostgre.Entity;

import com.example.porjectofinalpostgre.dto.CommentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter @Setter
@NoArgsConstructor
@Data
@Entity
@Table(name="products")
public class Product implements Serializable {
    //@JsonBackReference
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;


    @NotNull(message = "Nombre de producto requerido")
    @Basic(optional = false)
    private String nombre;

    private Float oferta;


    @Lob
    @NotNull(message = "Descripción de producto requerido")
    private String descripcion;

    @NotNull(message = "precio requerido")
    @Basic(optional = false)
    private Double precio;


    @NotNull(message = "Tipo requerido")
    @Basic(optional = false)
    private String tipo;
    @NotNull(message = "categoria requeridoa")
    @Basic(optional = false)
    private String categoria;

    @NotNull(message = "utilidad requeridoa")
    @Basic(optional = false)
    private String utilidad;

    @NotNull(message = "marca requerida")
    @Basic(optional = false)
    private String marca;

    @NotNull(message = "sexo requerido")
    @Basic(optional = false)
    private String sexo;

    @ElementCollection
    @CollectionTable(name = "colorProds", joinColumns = @JoinColumn(name =
            "id"))
    private List<String> color;

    @ElementCollection
    @CollectionTable(name = "tallaProds", joinColumns = @JoinColumn(name =
            "id"))
    private List<String> talla;

    @NotNull(message = "Tipo requerido")
    @Basic(optional = false)
    private String imagen;

    @JsonBackReference

    @OneToMany(mappedBy = "productId")
    private List<Comment> comments_products;

    /*

    @OneToMany(targetEntity = User.class,cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="comment_id")
    private List<String> comments = new ArrayList<>();

*/


        /*
    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();
    */
    public Product(String id,String nombre, Float oferta, String descripcion, Double precio, String tipo, String categoria, String utilidad, String marca, String sexo, List<String> color, List<String> talla, String imagen) {
        this.idProduct= Integer.valueOf(id);
        this.nombre = nombre;
        this.oferta = oferta;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo = tipo;
        this.categoria = categoria;
        this.utilidad = utilidad;
        this.marca = marca;
        this.sexo = sexo;
        this.color = color;
        this.talla = talla;
        this.imagen = imagen;
    }
}
