package com.example.porjectofinalpostgre;
/*
import com.example.porjectofinalpostgre.Controller.UserController;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DeleteMethodsTests {


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

    }

    private String token;
    @BeforeAll
    void getToken() throws Exception{
        MvcResult result=this.mvc.perform(post("/token").with(httpBasic("rafapr0001@gmail.com","Rafapr_01")))
                .andExpect(status().isOk())
                .andReturn();

        String jwt = result.getResponse().getContentAsString();
        this.token=jwt;
    }

    @Test
    void rootWhenUnauthenticatedThen401() throws Exception {
        this.mvc.perform(get("/"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void deleteUserById() throws Exception {

        long usersCount = userRepository.count();


        mvc.perform(delete("/users/1").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 1 ha sido eliminado de la base de datos"));

        mvc.perform(delete("/users/3").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 3 ha sido eliminado de la base de datos"));

        mvc.perform(delete("/users/2").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 2 ha sido eliminado de la base de datos"));



    }

    @Test
    void deleteProductById() throws Exception {

        long productsCount = productRepository.count();


        mvc.perform(delete("/products/1").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1 producto eliminado de API "));

        mvc.perform(delete("/products/6").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("6 producto eliminado de API "));


    }


    @Test
    void deleteOrderById() throws Exception {

        long ordersCount = orderRepository.count();


        mvc.perform(delete("/orders/3").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("3 pedido eliminadio de la API"));



    }


}
