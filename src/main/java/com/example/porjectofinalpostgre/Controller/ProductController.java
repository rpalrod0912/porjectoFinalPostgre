package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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
    public  Product findProductUsingNombre(@PathVariable String nombre){
        return service.getProductByNombre(nombre);
    }

    //Solo devuelvle las paginas existentes en la API no su contenido
    @GetMapping("/paginas")
    public List<String> findProductPages(){
        return service.getAllPages();
    }

    @GetMapping("/ofertas")
        public List<Product> findProductsBySale(){
            return service.getSales();
        }

    @GetMapping("/ofertas/paginas")
    public List<String> findSalesPages(){

            return service.getSalePages();

    }

    @GetMapping("/ofertas/paginas/{numPagina}")

    public List<Product> getPagedSalesProds(@PathVariable String numPagina){
        return  service.getSaleProductsPaged(numPagina);
    }

    //Devuelve el contenido de la pagina
    @GetMapping("/paginas/{numPagina}")
    public List<Product> getPageProducts(@PathVariable String numPagina){
        return service.getProductsPage(numPagina);
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
