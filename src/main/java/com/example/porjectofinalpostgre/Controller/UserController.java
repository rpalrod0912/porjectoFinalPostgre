package com.example.porjectofinalpostgre.Controller;


import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.OrderRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        user.setPwd(new BCryptPasswordEncoder().encode(user.getPwd()));
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
            foundUser.setPwd(new BCryptPasswordEncoder().encode(userRequest.getPwd()));
        }
        if(userRequest.getCp()!=null){
            foundUser.setCp(userRequest.getCp());
        }
        if(userRequest.getCiudad()!=null) {
            foundUser.setCiudad(userRequest.getCiudad());
        }
        if(userRequest.getProvincia()!=null){
            foundUser.setProvincia(userRequest.getProvincia());
        }
        if(userRequest.getDireccion()!=null){
            foundUser.setDireccion(userRequest.getDireccion());
        }
        if(userRequest.getFechaNac()!=null){
            foundUser.setFechaNac(userRequest.getFechaNac());
        }
        if(userRequest.getGenero()!=null){
            foundUser.setGenero(userRequest.getGenero());
        }
        if(userRequest.getInfo()!=null){
            foundUser.setInfo(userRequest.getInfo());
        }
        if(userRequest.getPhone()!=null){
            foundUser.setPhone(userRequest.getPhone());
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

        return userRepository.findById(idUser);
    }

    @GetMapping("/email/{mail}")
    public  User getUserByMail(@PathVariable String mail){
        return userRepository.findByMail(mail);
    }

    @GetMapping("/firebase/{firebaseId}")
    public  User getUserByFirebase(@PathVariable String firebaseId){
        return userRepository.findByFirebaseId(firebaseId);
    }

    @DeleteMapping("/{idUser}")
    public String deleteUserById(@PathVariable String idUser){


        orderService.deleteOrderByUserId(userRepository.findById(idUser).get());
        userRepository.deleteById(idUser);
        return "El usuario "+idUser+" ha sido eliminado de la base de datos";
    }

}
