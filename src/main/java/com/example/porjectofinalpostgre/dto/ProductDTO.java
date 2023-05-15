package com.example.porjectofinalpostgre.dto;

import com.example.porjectofinalpostgre.Entity.Comment;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDTO {

    private Integer idProduct;



    private String nombre;

    private Float oferta;


    private String descripcion;


    private Double precio;



    private String tipo;

    private String categoria;


    private String utilidad;

    private String marca;


    private String sexo;


    private List<String> color;


    private List<String> talla;


    private String imagen;

    private  List<CommentDTO> comentarios;


}
