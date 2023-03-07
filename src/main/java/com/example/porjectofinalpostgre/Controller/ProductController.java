package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired

    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public Product create(@RequestBody Product product){

        return repository.save(product);
    }

    @GetMapping
    public List<Product> getProducts(){
        return service.getAllProducts();
    }

    @GetMapping("/{productId}")
    public Product getProduct(@PathVariable Integer productId){
        return service.getProductById(productId);
    }

    @GetMapping("/precio/{precio}")
    public List<Product> findProductUsingPrecio(@PathVariable Double precio){
        return service.getProductByPrecio(precio);
    }
    @GetMapping("/nombre/{nombre}")
    public  List<Product> findProductUsingNombre(@PathVariable String nombre){
        return service.getProductByNombre(nombre);
    }

    @GetMapping("marca/{marca}")
    public List<Product> findProductsUsingMarca(@PathVariable String marca){
        return service.getProductByMarca(marca);
    }
    @PutMapping("/{productId}")
    public Product modifyProduct(@PathVariable Integer productId,@RequestBody Product productRequest){
        return service.updateProduct(productId,productRequest);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable Integer productId){
        return service.deleteProduct(productId);
    }


}
