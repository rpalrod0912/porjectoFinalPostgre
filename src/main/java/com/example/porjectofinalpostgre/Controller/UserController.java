package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public String create(@RequestBody User user){
        userRepository.save(user);
        return "Usuario creado";
    }

    @GetMapping
    public List<User> getAllUsers(){
        List<User> users=userRepository.findAll();
        return users;
    }

}
