/*
package com.example.porjectofinalpostgre.Seeders;

import com.example.porjectofinalpostgre.Controller.OrderController;
import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.Service.OrderService;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Seeder implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderController orderController;

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();
        List<String> coloresp1= new ArrayList<>();
        coloresp1.add("Blanco");
        coloresp1.add("Negro");

        List<String> coloresp2= new ArrayList<>();
        coloresp2.add("Rojo");
        coloresp2.add("Blanco");
        coloresp2.add("Negro");
        coloresp2.add("Azul");

        List<String> coloresp3= new ArrayList<>();
        coloresp3.add("Verde");
        coloresp3.add("Gris");
        coloresp3.add("Blanco");

        List<String> coloresp4= new ArrayList<>();
        coloresp4.add("Azul");
        coloresp4.add("Negro");
        coloresp4.add("Gris");

        List<String> coloresp5 = new ArrayList<>();
        coloresp5.add("Verde");
        coloresp5.add("Negro");

        List<String> coloresp6 = new ArrayList<>();
        coloresp6.add("Rojo");



        List<String> tallas = new ArrayList<>();
        tallas.add("XL");
        tallas.add("L");
        tallas.add("M");
        tallas.add("S");
        tallas.add("S");

        List<String> tallasp1 = new ArrayList<String>();
        tallasp1.add("35");
        tallasp1.add("38");
        tallasp1.add("39");
        tallasp1.add("40");
        tallasp1.add("40.5");
        tallasp1.add("41");
        tallasp1.add("42");
        tallasp1.add("44");
        tallasp1.add("45");
        tallasp1.add("46");
        tallasp1.add("47");
        tallasp1.add("48");
        tallasp1.add("49");

        List<String> tallasp2 = new ArrayList<String>();
        tallasp2.add("37");
        tallasp2.add("36");
        tallasp2.add("38");
        tallasp2.add("40");

        List<String> tallasp3 = new ArrayList<String>();
        tallasp3.add("38");
        tallasp3.add("39");
        tallasp3.add("44");
        tallasp3.add("45");
        tallasp3.add("46");
        tallasp3.add("47");
        tallasp3.add("48");
        tallasp3.add("49");
        tallasp3.add("50");

        List<String> tallasp4 = new ArrayList<String>();
        tallasp4.add("S");
        tallasp3.add("M");
        tallasp3.add("xL");

        List<String> tallasp5 = new ArrayList<String>();
        tallasp5.add("M");
        tallasp5.add("L");

        List<String> tallasp6 = new ArrayList<String>();

        tallasp5.add("L");
        tallasp5.add("XL");


/*
        Product p1 = new Product(
                "Air Force 1",
                10.0f,
                "El fulgor sigue vivo con un diseño de baloncesto original. Con un diseño que combina la comodidad para la cancha con un estilo urbano, estas zapatillas dan un nuevo giro a su ya característico estilo, su confección inspirada en los 80, sus detalles llamativos y su estilo impecable.",
                120.0d,
                "Zapatos",
                "Deportivas",
                "Casual",
                "Nike",
                "Unisex",
                coloresp1,
                tallasp1,
                "https://i.ibb.co/D55f9QN/af1.png"

        );
        Product p2 = new Product(
                "Air Jordan 1",
                null,
                "Este modelo reúne el diseño clásico de las AJ1 y los colores originales de las AJ3 para celebrar el 35 cumpleaños de las Air Jordan 3. El diseño se inspira en las zapatillas del 85: piel premium, una zona del tobillo de perfil alto y la etiqueta cosida tan popular de la lengüeta. ",
                140.0d,
                "Zapatos",
                "Deportivas",
                "Baloncesto",
                "Nike",
                "Unisex",
                coloresp2,
                tallasp2,
                "https://i.ibb.co/PhQVSy1/jordan1.png"
        );

        Product p3 = new Product(
                "New Balanace 550",
                15.0f,
                "El regreso de una leyenda. Originalmente usada por los profesionales, la nueva 550 rinde homenaje a la original de 1989 con detalles clásicos evocadores de la época: sencilla, clara y fiel a su legado.",
                150.0d,
                "Zapatos",
                "Deportivas",
                "Casual",
                "NewBalance",
                "Unisex",
                coloresp3,
                tallasp3,
                "https://i.ibb.co/rdmhhGf/New-Balance550.png"
        );

        Product p4 = new Product(
                "Sudadera Nike SB Icon",
                26.0f,
                "La sudadera con capucha Nike SB Icon para hombre combina la comodidad informal de una sudadera de chándal con capucha con la suavidad del tejido Fleece cepillado. Es perfecta para el día a día.",
                32.47d,
                "Sudadera",
                "Deportivas",
                "Casual",
                "Nike",
                "Hombre",
                coloresp4,
                tallasp4,
                "https://i.ibb.co/j6Qp4YV/Sud-Nike-SBIcon.png"
        );

        Product p5 = new Product(
                "Chandal Nike Básico",
                null,
                "El chándal Nike Sportswear Sport Essentials ofrece un look deportivo perfecto para el día a día. Confeccionado con un suave tejido Knit de poliéster, esta combinación informal ofrece un look perfecto.",
                68.99d,
                "Sudadera",
                "Deportivas",
                "Casual",
                "Nike",
                "Hombre",
                coloresp5,
                tallasp5,
                "https://i.ibb.co/4ZJ6rvP/chandal-Nike-VErde.png"
        );

        Product p6 = new Product(
                "Chandal Adidas Red",
                9.0f,
                "Se ha dicho que adidas lanzó el primer chándal en 1964. No podemos confirmarlo, pero sí sabemos con certeza que las 3 bandas han estado presentes en estadios, canchas y aceras durante décadas. Ha sido la chaqueta favorita de futbolistas y amantes de la música. Ahora también será la tuya. Luce un diseño cómodo confeccionado en Parley Ocean Plastic, un hilo creado a partir de residuos plásticos.",
                49.99d,
                "Sudadera",
                "Deportivas",
                "Casual",
                "Nike",
                "Unisex",
                coloresp6,
                tallasp6,
                "https://i.ibb.co/RBgPstx/sudadera-Roja-Adidas.png"
        );

        productRepository.save(p1);
        productRepository.save(p2);
        productRepository.save(p3);
        productRepository.save(p4);
        productRepository.save(p5);
        productRepository.save(p6);
  User u1 = new User();
        u1.setNombre("Rafael");
        u1.setApellidos("Palomino");
        u1.setMail("holajavi@gmail.com");
        u1.setPwd(new BCryptPasswordEncoder().encode("suspenso_01"));

        userRepository.save(u1);


        List<Integer> productso1= new ArrayList<>();
        productso1.add(p1.getIdProduct());
        productso1.add(p4.getIdProduct());



        List<Integer> productso2= new ArrayList<>();
        productso2.add(p1.getIdProduct());
        productso2.add(p4.getIdProduct());
        productso2.add(p3.getIdProduct());
        productso2.add(p2.getIdProduct());

        List<Integer> productso3= new ArrayList<>();
        productso3.add(p5.getIdProduct());

        List<Integer> productso4= new ArrayList<>();
        productso4.add(p2.getIdProduct());
        productso4.add(p6.getIdProduct());

        List<Integer> productso5= new ArrayList<>();
        productso5.add(p3.getIdProduct());
        productso5.add(p2.getIdProduct());
        productso5.add(p6.getIdProduct());

        List<Integer> quantityo1= new ArrayList<>();
        quantityo1.add(1);
        quantityo1.add(3);

        List<Integer> quantityo2= new ArrayList<>();
        quantityo2.add(1);
        quantityo2.add(2);
        quantityo2.add(3);
        quantityo2.add(1);

        List<Integer> quantityo3= new ArrayList<>();
        quantityo3.add(2);

        List<Integer> quantityo4= new ArrayList<>();
        quantityo4.add(1);
        quantityo4.add(2);

        List<Integer> quantityo5= new ArrayList<>();
        quantityo5.add(1);
        quantityo5.add(2);
        quantityo5.add(3);


        OrdersDTO o1= new OrdersDTO();
        o1.setUserid(u1.getIdUser().toString());
        o1.setProducts(productso1);
        o1.setQuantity(quantityo1);

        OrdersDTO o2= new OrdersDTO();
        o2.setUserid(u3.getIdUser().toString());
        o2.setProducts(productso2);
        o2.setQuantity(quantityo2);

        OrdersDTO o3= new OrdersDTO();
        o3.setUserid(u3.getIdUser().toString());
        o3.setProducts(productso3);
        o3.setQuantity(quantityo3);

        OrdersDTO o4= new OrdersDTO();
        o4.setUserid(u1.getIdUser().toString());
        o4.setProducts(productso4);
        o4.setQuantity(quantityo4);

        OrdersDTO o5= new OrdersDTO();
        o5.setUserid(u1.getIdUser().toString());
        o5.setProducts(productso5);
        o5.setQuantity(quantityo5);

        orderController.createOrder(o1);
        orderController.createOrder(o2);
        orderController.createOrder(o3);
        orderController.createOrder(o4);
        orderController.createOrder(o5);

        orderController.deleteOrder("1");







    }
}
*/
