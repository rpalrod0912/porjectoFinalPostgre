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

import java.util.*;

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
        //para saber en que indice nos encotnramos utilizadno un enhacend loop
        Integer contador=0;
        List <Integer> listaCantidades= order.getQuantity();
        List <Integer> listaIdProds= order.getProducts();
        List <Product> listaProds= new ArrayList<Product>();
        List<Double> listaPrecios=new ArrayList<Double>();
        for (Integer product_id : listaIdProds) {
            System.out.println(product_id);

            Double precioProducto=listaCantidades.get(contador)*productRepository.findById(product_id).get().getPrecio();
            listaPrecios.add(precioProducto);
            listaProds.add(productRepository.findById(product_id).get());
            //newOrder.getProducts().add(productRepository.findById(product_id).get());
            contador++;
        }
        Set <Product> arrayToSet= new LinkedHashSet<>((listaProds));
        newOrder.setProducts(arrayToSet);
        newOrder.setQuantity(order.getQuantity());
        newOrder.setPrices(listaPrecios);
        Double precioTotal=newOrder.getPrices().stream().mapToDouble(Double::valueOf).sum();
        newOrder.setPrecioFinal(precioTotal);

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
            newDto.setPrices(order.getPrices());
            newDto.setTotalPrice(order.getPrecioFinal());
            newDto.setTotalPrice(order.getPrecioFinal());
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
        //newDto.setTotalPrice(order.getPrices().stream().mapToDouble(Double::valueOf).sum());
        newDto.setProducts(listaIdProd);
        newDto.setQuantity(order.getQuantity());
        newDto.setPrices(order.getPrices());
        newDto.setTotalPrice(order.getPrecioFinal());
        return newDto;
    }
    public String deleteOrder(String orderId){
        orderRepository.deleteById(orderId);
        return orderId+" pedido eliminadio de la API";
    }


}
