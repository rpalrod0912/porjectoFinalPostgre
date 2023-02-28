package com.example.porjectofinalpostgre.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor
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

    public User(String nombre, String apellidos, String mail, String pwd) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.mail = mail;
        this.pwd = pwd;
    }
}
