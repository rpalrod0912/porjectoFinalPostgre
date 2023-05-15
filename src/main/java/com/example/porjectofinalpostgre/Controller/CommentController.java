package com.example.porjectofinalpostgre.Controller;

import com.example.porjectofinalpostgre.Entity.Comment;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Entity.User;
import com.example.porjectofinalpostgre.Repository.CommentRepository;
import com.example.porjectofinalpostgre.Service.CommentService;
import com.example.porjectofinalpostgre.Service.OrderService;
import com.example.porjectofinalpostgre.dto.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    /*ESTRUCTURA POST
    {
    "text":"Este es un comentario",
    "comments_products": 10,
	"userId_comments":20
}
    */

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<CommentDTO> getAllComments(){
        return commentService.showAllComments();
    }

    @GetMapping("/{productId}")
    public List<CommentDTO> getCommentsByProduct(@PathVariable Product productId){
        return commentService.showCommentsByProduct(productId);
    }

    @PostMapping
    public CommentDTO create(@RequestBody CommentDTO comment){
        return commentService.createCommentDTO(comment);
    }
}
