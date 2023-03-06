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
    void addUsersTest() throws Exception{
        Long usersCount= userRepository.count();

        String testUser = "{\"nombre\":\"Juan\",\"apellidos\":\"Antonio\",\"mail\":\"juan01@gmail.com\",\"pwd\":\"miContraseña222\"}";
        String testUser2 = "{\"nombre\":\"Bartus\",\"apellidos\":\"bartusito\",\"mail\":\"bartusito@hotmail.com\",\"pwd\":\"aprobado321\"}";


        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(testUser))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Antonio"))
                .andExpect(jsonPath("$.mail").value("juan01@gmail.com"))
                .andExpect(jsonPath("$.pwd").value("miContraseña222"));


        mvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON).content(testUser2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Bartus"))
                .andExpect(jsonPath("$.apellidos").value("bartusito"))
                .andExpect(jsonPath("$.mail").value("bartusito@hotmail.com"))
                .andExpect(jsonPath("$.pwd").value("aprobado321"));

        assert  userRepository.count()== usersCount+2;
    }


    @Test
    void addProductsTest() throws Exception{
        Long productsCount=productRepository.count();

        String testProduct="{\"nombre\":\"Juan\",\"apellidos\":\"Antonio\",\"mail\":\"juan01@gmail.com\",\"pwd\":\"miContraseña222\"}";

    }

}
