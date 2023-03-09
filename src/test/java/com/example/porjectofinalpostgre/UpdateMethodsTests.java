package com.example.porjectofinalpostgre;

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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UpdateMethodsTests {


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
    void updateUserTest() throws Exception{

        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

        String testUser = "{\"nombre\":\"Juan\",\"apellidos\":\"Antonio\",\"mail\":\"juan01@gmail.com\",\"pwd\":\"miContraseña222\"}";
        String testUser2 = "{\"nombre\":\"Bartus\",\"mail\":\"bartusito@hotmail.com\"}";

        mvc.perform(put("/users/1")
                .header("Authorization","Bearer "+token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(testUser))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Antonio"))
                .andExpect(jsonPath("$.mail").value("juan01@gmail.com"))
                .andExpect(jsonPath("$.pwd").value(userRepository.findByIdUser("1").getPwd()));

        mvc.perform(put("/users/2")
                        .header("Authorization","Bearer "+token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(testUser2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Bartus"))
                .andExpect(jsonPath("$.apellidos").value("Gonzalez"))
                .andExpect(jsonPath("$.mail").value("bartusito@hotmail.com"))
                .andExpect(jsonPath("$.pwd").value(userRepository.findByIdUser("2").getPwd()));

    }

    @Test
    void updateProductTest() throws Exception{


        String testProduct="{\"nombre\":\"Zapatos Basicos\",\"oferta\":10.0,\"descripcion\":\"Zapatos Básicos\",\"precio\":40.00,\"tipo\":\"Zapatos\",\"categoria\":\"Deportivas\",\"utilidad\":\"Casual\",\"marca\":\"Mercadona\",\"sexo\":\"Unisex\",\"color\":[\"Blanco\",\"Negro\"],\"talla\":[\"24\",\"39\"],\"imagen\":\"ninguna\"}";
        List<String> colorList=new ArrayList<>();
        colorList.add("Blanco");
        colorList.add("Negro");
        List<String> tallaList=new ArrayList<>();
        tallaList.add("24");
        tallaList.add("39");



        String testProduct2="{\"nombre\":\"Camisa Negra\",\"oferta\":null,\"descripcion\":\"Camisa Negra Básica\",\"precio\":30.00,\"tipo\":\"Camiseta\",\"categoria\":\"Deportivas\",\"utilidad\":\"Casual\",\"marca\":\"Quechua\",\"sexo\":\"Hombre\",\"color\":[\"Negro\"],\"talla\":[\"S\",\"L\"],\"imagen\":\"ninguna\"}";

        List<String> colorList2=new ArrayList<>();
        colorList2.add("Negro");

        List<String> tallaList2=new ArrayList<>();
        tallaList2.add("S");
        tallaList2.add("L");

        mvc.perform(put("/products/1").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testProduct))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Zapatos Basicos"))
                .andExpect(jsonPath("$.oferta").value(10.0))
                .andExpect(jsonPath("$.descripcion").value("Zapatos Básicos"))
                .andExpect(jsonPath("$.precio").value(40.0))
                .andExpect(jsonPath("$.tipo").value("Zapatos"))
                .andExpect(jsonPath("$.categoria").value("Deportivas"))
                .andExpect(jsonPath("$.utilidad").value("Casual"))
                .andExpect(jsonPath("$.marca").value("Mercadona"))
                .andExpect(jsonPath("$.sexo").value("Unisex"))
                .andExpect(jsonPath("$.color").value(colorList))
                .andExpect(jsonPath("$.talla").value(tallaList))
                .andExpect(jsonPath("$.imagen").value("ninguna"));


        mvc.perform(put("/products/3").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testProduct2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Camisa Negra"))
                .andExpect(jsonPath("$.oferta").value(IsNull.nullValue()))
                .andExpect(jsonPath("$.descripcion").value("Camisa Negra Básica"))
                .andExpect(jsonPath("$.precio").value(30.0))
                .andExpect(jsonPath("$.tipo").value("Camiseta"))
                .andExpect(jsonPath("$.categoria").value("Deportivas"))
                .andExpect(jsonPath("$.utilidad").value("Casual"))
                .andExpect(jsonPath("$.marca").value("Quechua"))
                .andExpect(jsonPath("$.sexo").value("Hombre"))
                .andExpect(jsonPath("$.color").value(colorList2))
                .andExpect(jsonPath("$.talla").value(tallaList2))
                .andExpect(jsonPath("$.imagen").value("ninguna"));


    }

}
