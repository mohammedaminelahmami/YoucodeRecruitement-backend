package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Request.CommentRequest;
import com.example.youcodeRecruitment.Service.CommentService;
import com.example.youcodeRecruitment.dto.CommentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{id}") // idDocument
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@PathVariable String id, @RequestBody @Valid CommentRequest commentRequest) {
        commentService.createComment(commentRequest, Long.parseLong(id));
    }

    @PutMapping("/comments/{id}") // idComment
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable String id ,@RequestBody @Valid CommentRequest commentRequest) {
        commentService.updateComment(commentRequest, Long.parseLong(id));
    }

    @DeleteMapping("/comments/{id}") // idComment
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        commentService.deleteComment(Long.parseLong(id));
    }

    @GetMapping("/comment/{id}") // idComment
    @ResponseStatus(HttpStatus.OK)
    public CommentDTO getOne(@PathVariable String id) {
        return commentService.getOneComment(Long.parseLong(id));
    }

    @GetMapping("/comments/{id}") // idDocument
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDTO> getAllById(@PathVariable String id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int limit) {
        return commentService.getAllCommentsById(Long.parseLong(id), page, limit);
    }
}
