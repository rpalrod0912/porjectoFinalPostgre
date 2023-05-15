package com.example.porjectofinalpostgre.Controller;

import com.example.porjectofinalpostgre.Entity.Comment;
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


    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> getAllUsers(){
        List<Comment> comments= commentRepository.findAll();
        return comments;
    }

    @PostMapping
    public CommentDTO create(@RequestBody CommentDTO comment){
        return commentService.createCommentDTO(comment);
    }
}
