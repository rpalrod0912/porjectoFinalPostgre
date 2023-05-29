package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface ProductRepository extends JpaRepository<Product,Integer> {
    @Query(value = "SELECT * FROM PRODUCTS",nativeQuery = true)
    List<Product> findProducts();
    Product findByIdProduct(Integer id);
    List<Product> findByPrecio(Double precio);


    List<Product> findByMarca(String marca);

    Product findByNombre(String nombre);
}
