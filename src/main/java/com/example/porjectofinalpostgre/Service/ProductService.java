package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.OrderItem;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Transactional
@Service
public class ProductService {


    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public Product addProduct(Product product){


        return productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }

    public Product getProductById(Integer productId){
        System.out.println(productId);
        return productRepository.findById(productId).get();
    }

    public  List<Product> getProductByMarca(String marca){
        return productRepository.findByMarca(marca);
    }

    public List<Product> getProductByPrecio(Double precio){
        return productRepository.findByPrecio(precio);
    }

    public  List<Product> getProductByNombre(String nombre){
        return productRepository.findByNombre(nombre);
    }


    public Product updateProduct(Product productRequest){
        //obtener docs de la DB
        //Dar valor de lar equisit al existete documento/entidad/objeto
        Product existingProduct=productRepository.findByIdProduct(productRequest.getIdProduct());
        existingProduct.setDescripcion(productRequest.getDescripcion());
        existingProduct.setNombre(productRequest.getNombre());
        existingProduct.setCategoria(productRequest.getCategoria());
        existingProduct.setPrecio(productRequest.getPrecio());
        existingProduct.setColor(productRequest.getColor());
        existingProduct.setMarca(productRequest.getMarca());
        existingProduct.setTalla(productRequest.getTalla());
        existingProduct.setTipo(productRequest.getTipo());
        existingProduct.setUtilidad(productRequest.getUtilidad());
        return productRepository.save(existingProduct);
    }

    public String deleteProduct(Integer productId){
        List<OrderItem> productIdOrders=orderItemRepository.findByIdProduct(productId);
        for(OrderItem productIdOrder  : productIdOrders){
            orderService.deleteOrder(productIdOrder.getOrderId().toString());
        }
        productRepository.deleteById(productId);
        return productId+" producto eliminado de API ";
    }
}
