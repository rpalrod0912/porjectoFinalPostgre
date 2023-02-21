package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,String> {

    Product findByIdProduct(Integer id);
    List<Product> findByPrecio(Double precio);


    List<Product> findByMarca(String marca);

    List<Product> findByNombre(String nombre);
}
