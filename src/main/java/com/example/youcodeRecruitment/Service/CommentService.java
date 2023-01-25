package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Comment;
import com.example.youcodeRecruitment.Entity.Document;
import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Repository.CommentRepository;
import com.example.youcodeRecruitment.Repository.DocumentRepository;
import com.example.youcodeRecruitment.Request.CommentRequest;
import com.example.youcodeRecruitment.dto.CommentDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final DocumentRepository documentRepository;
    private final IMapperDto<CommentDTO, Comment> mapperDTO;
    private final AuthService authService;

    public void createComment(CommentRequest commentRequest, Long id) {
        HR hr = authService.getAuthenticatedHR(); // get authenticated HR
        if(hr == null) throw new RuntimeException("HR not found"); // if HR not found throw exception
        Optional<Document> document = documentRepository.findById(id); // get document by id

        if(document.isPresent()) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentRequest, comment);
            comment.setHr(hr);
            comment.setDocument(document.get());
            commentRepository.save(comment);
        }else{
            throw new RuntimeException("Document not found");
        }
    }

    public void updateComment(CommentRequest commentRequest, Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            comment.get().setBody(commentRequest.getBody());
            commentRepository.save(comment.get());
        }else{
            throw new RuntimeException("Comment not found");
        }
    }

    public void deleteComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            commentRepository.delete(comment.get());
        }else {
            throw new RuntimeException("Comment not found");
        }
    }

    public CommentDTO getOneComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()) {
            return mapperDTO.convertToDTO(comment.get(), CommentDTO.class);
        }else {
            throw new RuntimeException("Comment not found");
        }
    }

    public List<CommentDTO> getAllCommentsById(Long id, int page, int limit) {
        if(page > 0) page--;
        Optional<Document> document = documentRepository.findById(id);
        if(document.isPresent()) {
            List<Comment> comments = commentRepository.findAllByDocument(document.get(), PageRequest.of(page, limit)).getContent();
            return mapperDTO.convertListToListDto(comments, CommentDTO.class);
        }else{
            throw new RuntimeException("Document not found");
        }
    }
}

