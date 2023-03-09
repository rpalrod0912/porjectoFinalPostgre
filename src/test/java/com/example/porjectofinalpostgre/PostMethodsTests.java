package com.example.porjectofinalpostgre;

import com.example.porjectofinalpostgre.Controller.UserController;
import com.example.porjectofinalpostgre.Repository.OrderItemRepository;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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
    void addUsersTest() throws Exception{
        Long usersCount= userRepository.count();

        String testUser = "{\"nombre\":\"Juan\",\"apellidos\":\"Antonio\",\"mail\":\"juan01@gmail.com\",\"pwd\":\"miContraseña222\"}";
        String testUser2 = "{\"nombre\":\"Bartus\",\"apellidos\":\"bartusito\",\"mail\":\"bartusito@hotmail.com\",\"pwd\":\"aprobado321\"}";


        mvc.perform(post("/users").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testUser))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.apellidos").value("Antonio"))
                .andExpect(jsonPath("$.mail").value("juan01@gmail.com"))
                .andExpect(jsonPath("$.pwd").value("miContraseña222"));


        mvc.perform(post("/users").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testUser2))
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

        mvc.perform(post("/products").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testProduct))
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


        mvc.perform(post("/products").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testProduct2))
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

    @Test
    void addOrdersTest() throws Exception{
        String testOrder="{\"userid\":\"1\",\"products\":[6,3,2],\"quantity\":[2,1,4]}";

        List<Double> pricesList= new ArrayList<>();
        pricesList.add(99.98);
        pricesList.add(150.0);
        pricesList.add(560.0);

        List<Integer> quantityList = new ArrayList<>();
        quantityList.add(2);
        quantityList.add(1);
        quantityList.add(4);


        String testOrder2="{\"userid\":\"3\",\"products\":[2,1,3],\"quantity\":[4,5,2]}";

        List<Double> pricesList2= new ArrayList<>();
        pricesList2.add(560.0);
        pricesList2.add(600.0);
        pricesList2.add(300.0);


        List<Integer> quantityList2 = new ArrayList<>();
        quantityList2.add(4);
        quantityList2.add(5);
        quantityList2.add(2);

        mvc.perform(post("/orders").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testOrder))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(6))
                .andExpect(jsonPath("$.prices").value(pricesList))
                .andExpect(jsonPath("$.quantity").value(quantityList))
                .andExpect(jsonPath("$.precioFinal").value(809.98))
                .andExpect(jsonPath("$.userid.idUser").value(1));

        mvc.perform(post("/orders").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON).content(testOrder2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(7))
                .andExpect(jsonPath("$.prices").value(pricesList2))
                .andExpect(jsonPath("$.quantity").value(quantityList2))
                .andExpect(jsonPath("$.precioFinal").value(1460.0))
                .andExpect(jsonPath("$.userid.idUser").value(3));
    }

}
