package com.example.porjectofinalpostgre.Service;

import com.example.porjectofinalpostgre.Entity.Comment;
import com.example.porjectofinalpostgre.Entity.Product;
import com.example.porjectofinalpostgre.Repository.CommentRepository;
import com.example.porjectofinalpostgre.Repository.ProductRepository;
import com.example.porjectofinalpostgre.Repository.UserRepository;
import com.example.porjectofinalpostgre.dto.CommentDTO;
import com.example.porjectofinalpostgre.dto.OrdersDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        System.out.println(comment);

        System.out.println(comment.getRating());
        if(comment.getRating()>5){
            comment.setRating(5);
        }if(comment.getRating()<0){
            comment.setRating(0);
        }
            Comment originalComment= new Comment();
            originalComment.setText(comment.getText());
            originalComment.setUserId(userRepository.findByIdUser(comment.getUserId()));
            originalComment.setProductId(productRepository.findByIdProduct(Integer.valueOf(comment.getProductId())));
            originalComment.setRating(comment.getRating());
        //originalComment.setText();
        //this.commentRepository.save(originalComment);
        commentRepository.save(originalComment);
        return  comment;
    }

    public List<CommentDTO> showAllComments() {
        List <CommentDTO> listaEnviar =  new ArrayList<>();

        List<Comment> comments=commentRepository.findAll();
        for (Comment comment : comments){
            CommentDTO commentDTO= new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setText(comment.getText());
            commentDTO.setProductId(String.valueOf(comment.getProductId().getIdProduct()));
            commentDTO.setUserId(String.valueOf(comment.getUserId().getIdUser()));
            commentDTO.setRating(comment.getRating());
            listaEnviar.add(commentDTO);
        }
        return listaEnviar;
    }

    public List<CommentDTO> showCommentsByProduct(Product productId) {
        List <CommentDTO> listaEnviar =  new ArrayList<>();

        List<Comment> comments = commentRepository.findByProductId(productId);
        for (Comment comment : comments){
            CommentDTO commentDTO= new CommentDTO();
            commentDTO.setId(comment.getId());
            commentDTO.setUserNameLastName(comment.getUserId().getNombre()+" "+comment.getUserId().getApellidos());
            commentDTO.setText(comment.getText());
            commentDTO.setProductId(String.valueOf(comment.getProductId().getIdProduct()));
            commentDTO.setUserId(String.valueOf(comment.getUserId().getIdUser()));
            commentDTO.setRating(comment.getRating());
            listaEnviar.add(commentDTO);
        }

        return listaEnviar;
    }

    public String deleteById(String commentId) {
        commentRepository.deleteById(commentId);
        return "COMENTARIO ELIMINADO";
    }
}
