package com.example.porjectofinalpostgre.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serializable;


@Getter @Setter
@NoArgsConstructor
@Data
@Entity
@Table(name="users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @Column(name="nombre")
    private String nombre;
    @Column(name="apellidos")
    private String apellidos;

    @Column(name="mail")
    private String mail;
    @Column(name="pwd")
    private String pwd;

    @Column(name="firebaseId")
    private String firebaseId;
    @Column(name = "phone")
    private String phone;

    @Column(name="genero")
    private String genero;

    @Column(name="fechaNac")
    private String fechaNac;

    @Column(name="direccion")
    private String direccion;

    @Column(name="info")
    private String info;
    @Column(name="cp")
    private String cp;

    @Column(name="ciudad")
    private String ciudad;

    @Column(name = "provincia")
    private String provincia;




    public User(String idUser,String nombre, String apellidos, String mail, String pwd,String firebaseId,String phone,String direccion, String info, String cp, String ciudad, String provincia) {
        this.idUser= Integer.valueOf(idUser);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.pwd = new BCryptPasswordEncoder().encode(pwd);
        this.firebaseId=firebaseId;
        this.phone=phone;

        this.direccion=direccion;
        this.info=info;
        this.cp=cp;
        this.ciudad=ciudad;
        this.provincia=provincia;


    }


}
