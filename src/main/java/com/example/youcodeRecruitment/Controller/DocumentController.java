package com.example.youcodeRecruitment.Controller;

import com.example.youcodeRecruitment.Service.DocumentService;
import com.example.youcodeRecruitment.dto.DocumentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping("/document")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestParam("file") MultipartFile file, @RequestParam("type") String type) {
        documentService.uploadDocument(file, type);
    }

    @DeleteMapping("/documents/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        documentService.deleteDocument(Long.parseLong(id));
    }

    @GetMapping("/documents/documentId/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DocumentDTO getOneByDocument(@PathVariable String id) {
        return documentService.getOneDocument(Long.parseLong(id));
    }

    @GetMapping("/documents/candidateId/{idCandidate}")
    @ResponseStatus(HttpStatus.OK)
    // Todo : fix more than one document
    public DocumentDTO getOneByCandidate(@PathVariable String idCandidate) {
        return documentService.getOneByIdCandidate(Long.parseLong(idCandidate));
    }
}