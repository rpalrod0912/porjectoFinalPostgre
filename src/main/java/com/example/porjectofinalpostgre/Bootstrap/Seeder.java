package com.example.porjectofinalpostgre.Bootstrap;

import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Seeder implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        orderRepository.deleteAll();
        productRepository.deleteAll();
        userRepository.deleteAll();

        List<String> colores = new ArrayList<>();
        colores.add("Blanco");
        colores.add("Negro");
        List<String> tallas = new ArrayList<>();
        tallas.add("XL");
        tallas.add("L");
        tallas.add("M");
        tallas.add("S");
        tallas.add("S");

        Product p1 = new Product(
                "Pape",
                2.0f,
                "Papes",
                120.0d,
                "Zapas",
                "Deportivas",
                "ppp",
                "Nike",
                "M",
                colores,
                tallas,
                "ninguna"
        );
        Product p2 = new Product(
                "Papes",
                2.0f,
                "Papesitos",
                120.0d,
                "Zapas",
                "Deportivas",
                "pppe",
                "Nike",
                "F",
                colores,
                tallas,
                "ninguna"
        );
        productRepository.save(p1);
        productRepository.save(p2);

        User u1 = new User("pp", "ss", "a@a.a", "pestillo");
        userRepository.save(u1);

        Set<Product> productos = new HashSet<>();
        productos.add(p1);
        productos.add(p2);
        Order o1 = new Order();
        o1.setUser(u1);
        o1.setProducts(productos);
        orderRepository.save(o1);
    }
}
