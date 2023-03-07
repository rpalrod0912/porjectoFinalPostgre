package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByIdUser(String id);

    User findByMail(String mail);

}
