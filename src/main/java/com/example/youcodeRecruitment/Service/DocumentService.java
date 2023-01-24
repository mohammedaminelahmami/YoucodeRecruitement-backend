package com.example.youcodeRecruitment.Service;

import com.example.youcodeRecruitment.Entity.Candidate;
import com.example.youcodeRecruitment.Entity.Document;
import com.example.youcodeRecruitment.Repository.CandidateRepository;
import com.example.youcodeRecruitment.Repository.DocumentRepository;
import com.example.youcodeRecruitment.dto.DocumentDTO;
import com.example.youcodeRecruitment.dto.mapper.IMapperDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DocumentService{

    private final DocumentRepository documentRepository;
    private final CandidateRepository candidateRepository;
    private final IMapperDto<DocumentDTO, Document> mapperDto;

    public void uploadDocument(MultipartFile file) throws RuntimeException, IOException {
        // Save the file
        File dest = new File("C:\\Users\\YC\\Desktop\\LearnJava-Js-etc\\youcodeRecruitment\\uploadDocument\\"+file.getOriginalFilename());
        file.transferTo(dest.toPath());
        Long id = 1L;
        Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
        System.out.println("- ".repeat(20)+file.getContentType()+" -".repeat(20));
        Document document = new Document();
        document.setType(file.getContentType());
        document.setPath(dest.toPath().toString());
        document.setCandidate(candidate);
        documentRepository.save(document); // save the document
    }

    public void deleteDocument(Long id){
        Optional<Document> document = documentRepository.findById(id);
        if(document.isPresent()){
            documentRepository.delete(document.get());
        }else{
            throw new RuntimeException("Document not found");
        }
    }

    public DocumentDTO getOneDocument(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if(document.isPresent()){
            return mapperDto.convertToDTO(document.get(), DocumentDTO.class);
        }else{
            throw new RuntimeException("Document not found");
        }
    }

    public DocumentDTO getOneByIdCandidate(Long idCandidate) {
        // Todo : fix more than one document
        Candidate candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> new RuntimeException("Candidate not found"));
        Optional<Document> document = documentRepository.findByCandidate(candidate);
        if(document.isPresent()){
            return mapperDto.convertToDTO(document.get(), DocumentDTO.class);
        }else{
            throw new RuntimeException("Document not found");
        }
    }
}
