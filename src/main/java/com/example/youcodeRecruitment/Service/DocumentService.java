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

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DocumentService{

    private final DocumentRepository documentRepository;
    private final CandidateRepository candidateRepository;
    private final IMapperDto<DocumentDTO, Document> mapperDto;
    private final AuthService authService;
    private final UploadFileService uploadFileService;

    public void uploadDocument(MultipartFile file, String type) throws RuntimeException {
        // Save the file
        String path = uploadFileService.getPath(file);

        Candidate candidate = authService.getAuthenticatedCandidate();
        if(candidate == null) throw new RuntimeException("Candidate not found");

        Document document = new Document();
        document.setType(type);
        document.setPath(path);
        document.setCandidate(candidate);
        documentRepository.save(document); // save the document

        int documentId = document.getId_document();
        int candidateId = candidate.getId_user();
        // Delete the old document
        candidate.getDocuments().forEach((doc) -> {if(doc.getType().equals(type) && doc.getCandidate().getId_user() == candidateId && doc.getId_document() != documentId){ documentRepository.delete(doc);}});
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