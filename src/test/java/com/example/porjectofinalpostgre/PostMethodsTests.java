package com.example.porjectofinalpostgre;

import com.example.porjectofinalpostgre.Controller.UserController;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostMethodsTests {


    @Autowired
    MockMvc mvc;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
        assert userRepository.count()==3;
        assert productRepository.count()==6;
        assert orderRepository.count()==4;
    }

    @Test
    void getAll() throws Exception{
        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idUser").value(1))
                .andExpect(jsonPath("$[0].nombre").value("Rafael"))
                .andExpect(jsonPath("$[0].mail").value("rafapr0001@gmail.com"));

        mvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[1].idUser").value(2))
                .andExpect(jsonPath("$[1].nombre").value("Calvorota"))
                .andExpect(jsonPath("$[1].mail").value("calvorota0001@gmail.com"));

        mvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[2].idProduct").value(3))
                .andExpect(jsonPath("$[2].nombre").value("New Balanace 550"))
                .andExpect(jsonPath("$[2].precio").value(150))
                .andExpect(jsonPath("$[2].oferta").value(15))
                .andExpect(jsonPath("$[2].tipo").value("Zapatos"))
        ;

        mvc.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[4].idProduct").value(5))
                .andExpect(jsonPath("$[4].nombre").value("Chandal Nike Básico"))
                .andExpect(jsonPath("$[4].precio").value(68.99))
                .andExpect(jsonPath("$[4].oferta").value(IsNull.nullValue()))
                .andExpect(jsonPath("$[4].tipo").value("Sudadera"))
        ;

        mvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(2))
                .andExpect(jsonPath("$[0].quantity.size()").value(4))
                .andExpect(jsonPath("$[0].orderproducts[0].orderId").value(2))
                .andExpect(jsonPath("$[0].orderproducts[0].idProduct").value(1))
                .andExpect(jsonPath("$[0].orderproducts[0].price").value(120.0))
                .andExpect(jsonPath("$[0].orderproducts[2].orderId").value(2))
                .andExpect(jsonPath("$[0].orderproducts[2].idProduct").value(3))
                .andExpect(jsonPath("$[0].orderproducts[2].price").value(450.0))
                .andExpect(jsonPath("$[0].totalPrice").value(882.47))
                .andExpect(jsonPath("$[0].user_id").value(3))
        ;


        mvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[3].id").value(5))
                .andExpect(jsonPath("$[3].quantity.size()").value(3))
                .andExpect(jsonPath("$[3].orderproducts[1].orderId").value(5))
                .andExpect(jsonPath("$[3].orderproducts[1].idProduct").value(2))
                .andExpect(jsonPath("$[3].orderproducts[1].price").value(280.0))
                .andExpect(jsonPath("$[3].orderproducts[2].orderId").value(5))
                .andExpect(jsonPath("$[3].orderproducts[2].idProduct").value(3))
                .andExpect(jsonPath("$[3].orderproducts[2].price").value(450.0))
                .andExpect(jsonPath("$[3].totalPrice").value(779.99))
                .andExpect(jsonPath("$[3].user_id").value(1))

        ;

    }




}
