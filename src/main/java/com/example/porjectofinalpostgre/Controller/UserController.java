package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping
    public User create(@RequestBody User user){

        return userRepository.save(user);
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
        userRepository.deleteById(idUser);
        return "El usuario "+idUser+"ha sido eliminado de la base de datos";
    }

}
