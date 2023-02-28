package com.example.porjectofinalpostgre.Bootstrap;

import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
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
    ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        if(productRepository.count()>0)
            return;

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
        productRepository.save(p1);
    }
}
