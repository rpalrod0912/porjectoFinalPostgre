package com.example.porjectofinalpostgre.Service;

import com.example.porjectofinalpostgre.Entity.Comment;
import com.example.porjectofinalpostgre.Repository.CommentRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.dto.CommentDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;


    public CommentDTO createCommentDTO(CommentDTO comment) {
            Comment originalComment= new Comment();
            originalComment.setText(comment.getText());
            originalComment.setUserId_comments(userRepository.findByIdUser(comment.getUserId_comments()));
            originalComment.setComments_products(productRepository.findByIdProduct(Integer.valueOf(comment.getComments_products())));
        //originalComment.setText();
        //this.commentRepository.save(originalComment);
        commentRepository.save(originalComment);
        return  comment;
    }
}
