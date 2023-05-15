package com.example.porjectofinalpostgre.Repository;

import com.example.porjectofinalpostgre.Entity.Comment;
import com.example.porjectofinalpostgre.Entity.OrderItem;
import com.example.porjectofinalpostgre.Entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment,String> {
    List<Comment> findByProductId(Product productId);


}
