package com.example.porjectofinalpostgre;

import com.example.porjectofinalpostgre.Controller.UserController;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
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
        assert userRepository.count()==3;
        assert productRepository.count()==6;
        assert orderRepository.count()==4;
    }

    @Test
    void deleteUserById() throws Exception{

        long usersCount = userRepository.count();



        mvc.perform(delete("/users/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 1 ha sido eliminado de la base de datos"));

        mvc.perform(delete("/users/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 3 ha sido eliminado de la base de datos"));

        mvc.perform(delete("/users/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("El usuario 2 ha sido eliminado de la base de datos"));

        assert userRepository.count() == usersCount-3;




    }


    @Test
    void deleteProductById() throws Exception{

        long productsCount = productRepository.count();

        mvc.perform(delete("/products/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("1 producto eliminado de API"));

        mvc.perform(delete("/products/3").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("3 producto eliminado de API"));

        mvc.perform(delete("/products/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("2 producto eliminado de API"));

        assert userRepository.count() == productsCount-3;

    }}
