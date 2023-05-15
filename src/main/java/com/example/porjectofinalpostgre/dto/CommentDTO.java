package com.example.porjectofinalpostgre.dto;

import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommentDTO implements Serializable {


    private Integer id;

    private String text;


    private String comments_products;

    private String userId_comments;
/*

    public CommentDTO(String idComment,String text,String prodComments,String userComments
    ){
        this.id= Integer.valueOf(idComment);
        this.text=text;
        this.comments_products=prodComments;
        this.userId_comments=userComments;



    }
    */

}
