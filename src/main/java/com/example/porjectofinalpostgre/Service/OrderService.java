package com.example.porjectofinalpostgre.Service;


import com.example.porjectofinalpostgre.Entity.Order;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;

    public Order addOrder(OrdersDTO order) {

        Order newOrder = new Order();

        System.out.println(order.getUser_id());
        String userId=order.getUser_id();
        System.out.println(userRepository.findById(userId).get());
        System.out.println("hola");
        newOrder.setUser_id(userRepository.findById(userId).get());
        for (Integer product_id : order.getProducts()) {
            System.out.println(product_id);
            newOrder.getProducts().add(productRepository.findById(product_id).get());
        }
        newOrder.setQuantity(order.getQuantity());

        return orderRepository.save(newOrder);
    }

    public List<OrdersDTO> getAllOrders(){
        //Lista vacia que contendra OrderDTO para enviar al cliente
        List <OrdersDTO> listaEnviar =  new ArrayList<>();
        //Lista completa con todos los Orders SIN SERIALIZAR
        List <Order> listaOrder = orderRepository.findAll();
        for (Order order: listaOrder){
            OrdersDTO newDto = new OrdersDTO();
            newDto.setId(order.getId());
            List <Integer> listaIdProd = new ArrayList<Integer>();
            newDto.setUser_id(order.getUser_id().getIdUser().toString());
            for (Product product : order.getProducts()){
                listaIdProd.add(product.getIdProduct());
            }
            newDto.setProducts(listaIdProd);
            newDto.setQuantity(order.getQuantity());
            System.out.println(newDto);
            listaEnviar.add(newDto);
        }
        System.out.println(orderRepository.findAll());
        return listaEnviar;
    }

    public OrdersDTO getOrderById(String orderId){
        Order order = orderRepository.findById(orderId).get();
        OrdersDTO newDto = new OrdersDTO();
        newDto.setId(order.getId());
        newDto.setUser_id(order.getUser_id().getIdUser().toString());
        List<Integer> listaIdProd = new ArrayList<Integer>();
        for (Product product : order.getProducts()){
            listaIdProd.add(product.getIdProduct());
        }
        newDto.setProducts(listaIdProd);
        newDto.setQuantity(order.getQuantity());
        return newDto;
    }
    public String deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
        return orderId+" pedido eliminadio de la API";
    }


}
