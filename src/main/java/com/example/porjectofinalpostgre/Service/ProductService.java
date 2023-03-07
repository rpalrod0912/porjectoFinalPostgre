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


    public Product updateProduct(Integer productID, Product productRequest){

        //obtener docs de la DB
        //Dar valor de lar equisit al existete documento/entidad/objeto
        Product existingProduct=productRepository.findByIdProduct(productID);
        if(productRequest.getDescripcion()!=null){
            existingProduct.setDescripcion(productRequest.getDescripcion());
        }
        if(productRequest.getNombre()!=null) {
            existingProduct.setNombre(productRequest.getNombre());
        }
        if(productRequest.getCategoria()!=null){
            existingProduct.setCategoria(productRequest.getCategoria());
        }
        if(productRequest.getPrecio()!=null){
            existingProduct.setPrecio(productRequest.getPrecio());
        }
        if(productRequest.getColor()!=null){
            existingProduct.setColor(productRequest.getColor());
        }
        if(productRequest.getMarca()!=null){
            existingProduct.setMarca(productRequest.getMarca());
        }
        if(productRequest.getTalla()!=null){
            existingProduct.setTalla(productRequest.getTalla());
        }
        if(productRequest.getTipo()!=null){
            existingProduct.setTipo(productRequest.getTipo());

        }
        if(productRequest.getUtilidad()!=null){
            existingProduct.setUtilidad(productRequest.getUtilidad());
        }
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
