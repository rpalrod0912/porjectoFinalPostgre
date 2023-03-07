package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;


    @PostMapping
    public User create(@RequestBody User user){

        return userRepository.save(user);
    }


    @PutMapping("/{idUser}")
    public User editUser(@PathVariable String idUser,@RequestBody User userRequest){
        User foundUser=userRepository.findByIdUser(idUser);


        if(userRequest.getNombre()!=null){
            foundUser.setNombre(userRequest.getNombre());
        }
        if(userRequest.getMail()!=null) {
            foundUser.setMail(userRequest.getMail());
        }
        if(userRequest.getApellidos()!=null){
            foundUser.setApellidos(userRequest.getApellidos());
        }
        if(userRequest.getPwd()!=null){
            foundUser.setPwd(userRequest.getPwd());
        }

        return userRepository.save(foundUser);

    }

    @GetMapping
    public List<User> getAllUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

    @GetMapping("/{idUser}")
    public Optional<User> getUserById(@PathVariable String idUser){
        Optional<User> user=userRepository.findById(idUser);
        return userRepository.findById(idUser);
    }
    @DeleteMapping("/{idUser}")
    public String deleteUserById(@PathVariable String idUser){


        orderService.deleteOrderByUserId(userRepository.findById(idUser).get());
        userRepository.deleteById(idUser);
        return "El usuario "+idUser+" ha sido eliminado de la base de datos";
    }

}
