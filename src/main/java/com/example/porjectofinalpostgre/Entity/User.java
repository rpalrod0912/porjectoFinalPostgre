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


    public User(String idUser,String nombre, String apellidos, String mail, String pwd,String firebaseId,String phone) {
        this.idUser= Integer.valueOf(idUser);
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.pwd = new BCryptPasswordEncoder().encode(pwd);
        this.firebaseId=firebaseId;
        this.phone=phone;
    }


}
