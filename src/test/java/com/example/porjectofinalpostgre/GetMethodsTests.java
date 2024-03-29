/*
package com.example.porjectofinalpostgre;

import com.example.porjectofinalpostgre.Controller.AuthController;
import com.example.porjectofinalpostgre.Service.TokenService;
import com.example.porjectofinalpostgre.security.SecurityConfig;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;


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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.token.Token;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GetMethodsTests {


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
	void rootWhenUnauthenticatedThen401() throws Exception {
		this.mvc.perform(get("/"))
				.andExpect(status().isUnauthorized());
	}


	@Test
	void contextLoads() {
	assert userRepository.count()==3;
	assert productRepository.count()==6;
	assert orderRepository.count()==4;
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
	void getAll() throws Exception{



		MvcResult result=this.mvc.perform(post("/token").with(httpBasic("rafapr0001@gmail.com","Rafapr_01")))
						.andExpect(status().isOk())
								.andReturn();

		String token = result.getResponse().getContentAsString();



		mvc.perform(get("/users").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].idUser").value(1))
				.andExpect(jsonPath("$[0].nombre").value("Rafael"))
				.andExpect(jsonPath("$[0].mail").value("rafapr0001@gmail.com"));


		mvc.perform(get("/users").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[1].idUser").value(2))
				.andExpect(jsonPath("$[1].nombre").value("Calvorota"))
				.andExpect(jsonPath("$[1].mail").value("calvorota0001@gmail.com"));

		mvc.perform(get("/products").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[2].nombre").value("New Balanace 550"))
				.andExpect(jsonPath("$[2].precio").value(150))
				.andExpect(jsonPath("$[2].oferta").value(15))
				.andExpect(jsonPath("$[2].tipo").value("Zapatos"))
		;

		mvc.perform(get("/products").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[4].nombre").value("Chandal Nike Básico"))
				.andExpect(jsonPath("$[4].precio").value(68.99))
				.andExpect(jsonPath("$[4].oferta").value(IsNull.nullValue()))
				.andExpect(jsonPath("$[4].tipo").value("Sudadera"))
		;

		mvc.perform(get("/orders").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
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
				.andExpect(jsonPath("$[0].totalPrice").value(774.94))
				.andExpect(jsonPath("$[0].userid").value(3))
		;


		mvc.perform(get("/orders").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[3].id").value(5))
				.andExpect(jsonPath("$[3].quantity.size()").value(3))
				.andExpect(jsonPath("$[3].orderproducts[1].orderId").value(5))
				.andExpect(jsonPath("$[3].orderproducts[1].idProduct").value(2))
				.andExpect(jsonPath("$[3].orderproducts[1].price").value(280.0))
				.andExpect(jsonPath("$[3].orderproducts[2].orderId").value(5))
				.andExpect(jsonPath("$[3].orderproducts[2].idProduct").value(6))
				.andExpect(jsonPath("$[3].orderproducts[2].price").value(149.97))
				.andExpect(jsonPath("$[3].totalPrice").value(579.97))
				.andExpect(jsonPath("$[3].userid").value(1))

				;

	}


	@Test
	void getById() throws Exception{
		mvc.perform(get("/users/1").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.idUser").value(1))
				.andExpect(jsonPath("$.nombre").value("Rafael"))
				.andExpect(jsonPath("$.mail").value("rafapr0001@gmail.com"));

		mvc.perform(get("/users/2").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.idUser").value(2))
				.andExpect(jsonPath("$.nombre").value("Calvorota"))
				.andExpect(jsonPath("$.mail").value("calvorota0001@gmail.com"));


		mvc.perform(get("/products/3").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.nombre").value("New Balanace 550"))
				.andExpect(jsonPath("$.precio").value(150))
				.andExpect(jsonPath("$.oferta").value(15))
				.andExpect(jsonPath("$.tipo").value("Zapatos"))
		;

		mvc.perform(get("/products/5").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.nombre").value("Chandal Nike Básico"))
				.andExpect(jsonPath("$.precio").value(68.99))
				.andExpect(jsonPath("$.oferta").value(IsNull.nullValue()))
				.andExpect(jsonPath("$.tipo").value("Sudadera"))
		;


		mvc.perform(get("/orders/2").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(2))
				.andExpect(jsonPath("$.quantity.size()").value(4))
				.andExpect(jsonPath("$.orderproducts[0].orderId").value(2))
				.andExpect(jsonPath("$.orderproducts[0].idProduct").value(1))
				.andExpect(jsonPath("$.orderproducts[0].price").value(120.0))
				.andExpect(jsonPath("$.orderproducts[2].orderId").value(2))
				.andExpect(jsonPath("$.orderproducts[2].idProduct").value(3))
				.andExpect(jsonPath("$.orderproducts[2].price").value(450.0))
				.andExpect(jsonPath("$.totalPrice").value(774.94))
				.andExpect(jsonPath("$.userid").value(3))
		;


		mvc.perform(get("/orders/5").header("Authorization","Bearer "+token).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id").value(5))
				.andExpect(jsonPath("$.quantity.size()").value(3))
				.andExpect(jsonPath("$.orderproducts[1].orderId").value(5))
				.andExpect(jsonPath("$.orderproducts[1].idProduct").value(2))
				.andExpect(jsonPath("$.orderproducts[1].price").value(280.0))
				.andExpect(jsonPath("$.orderproducts[2].orderId").value(5))
				.andExpect(jsonPath("$.orderproducts[2].idProduct").value(6))
				.andExpect(jsonPath("$.orderproducts[2].price").value(149.97))
				.andExpect(jsonPath("$.totalPrice").value(579.97))
				.andExpect(jsonPath("$.userid").value(1))

		;


	}}
*/