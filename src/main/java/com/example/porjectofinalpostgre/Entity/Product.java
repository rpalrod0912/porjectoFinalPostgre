package com.example.porjectofinalpostgre.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="products")
public class Product implements Serializable {
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

    private String imagen;


}
