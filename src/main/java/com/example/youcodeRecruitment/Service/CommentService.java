package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Comment;
import com.example.youcodeRecruitment.Entity.Document;
import com.example.youcodeRecruitment.Entity.HR;
import com.example.youcodeRecruitment.Repository.CommentRepository;
import com.example.youcodeRecruitment.Repository.DocumentRepository;
import com.example.youcodeRecruitment.Repository.HRRepository;
import com.example.youcodeRecruitment.Request.CommentRequest;
import com.example.youcodeRecruitment.dto.CommentDTO;
import com.example.youcodeRecruitment.dto.DocumentDTO;
import com.example.youcodeRecruitment.dto.HRDTO;
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
    private final HRRepository hrRepository;
    private final IMapperDto<CommentDTO, Comment> mapperDTO;
    private final IMapperDto<HRDTO, HR> mapperHrDTO;
    private final IMapperDto<DocumentDTO, Document> mapperDocumentDTO;

    public void createComment(CommentRequest commentRequest, Long idHR, Long idDocument) {
        Optional<Document> document = documentRepository.findById(idDocument);
        Optional<HR> hr = hrRepository.findById(idHR);

        CommentDTO commentDTO = new CommentDTO();

        if(hr.isPresent() && document.isPresent()) {
            HRDTO hrdto = mapperHrDTO.convertToDTO(hr.get(), HRDTO.class); // map hr to hrDTO
            DocumentDTO documentDTO = mapperDocumentDTO.convertToDTO(document.get(), DocumentDTO.class); // map document to documentDTO

            commentDTO.setHr(hrdto);
            commentDTO.setDocument(documentDTO);

        }else{
            throw new RuntimeException("HR or Document not found");
        }

        BeanUtils.copyProperties(commentRequest, commentDTO);

        Comment comment = mapperDTO.convertToEntity(commentDTO, Comment.class);
        if(comment != null) {
            commentRepository.save(comment);
        }else {
            throw new RuntimeException("Comment is null");
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

