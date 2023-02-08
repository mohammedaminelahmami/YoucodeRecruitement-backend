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
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final CandidateRepository candidateRepository;
    private final IMapperDto<DocumentDTO, Document> mapperDto;
    private final AuthService authService;
    private final UploadFileService uploadFileService;

    public void uploadDocument(MultipartFile file, String type) throws RuntimeException {
        if (file == null) throw new RuntimeException("File not found");
        // Save the file

        Candidate candidate = authService.getAuthenticatedCandidate();
        if (candidate == null) throw new RuntimeException("Candidate not found");

        String path = uploadFileService.getPath(file);
        if (path == null || path.equals("")) throw new RuntimeException("File not found");

        Optional<Document> findDoc = documentRepository.findByCandidateAndType(candidate, type);

        if (findDoc.isPresent()) {
            findDoc.get().setPath(path);
            documentRepository.save(findDoc.get());
        } else {
            Document document = new Document();
            System.out.println("---------------------------------1 -----------------------------");
            System.out.println(type);
            System.out.println("---------------------------------2 -----------------------------");
            document.setType(type);
            System.out.println("---------------------------------3 -----------------------------");
            document.setPath(path);
            document.setCandidate(candidate);
            documentRepository.save(document); // save the document
        }
    }

    public void deleteDocument(Long id) {
        Optional<Document> document = documentRepository.findById(id);
        if (document.isPresent()) {
            documentRepository.delete(document.get());
        } else {
            throw new RuntimeException("Document not found");
        }
    }

//    public DocumentDTO getOneDocument(Long id) {
//        Optional<Document> document = documentRepository.findById(id);
//        if (document.isPresent()) {
//            return mapperDto.convertToDTO(document.get(), DocumentDTO.class);
//        } else {
//            throw new RuntimeException("Document not found");
//        }
//    }

    public DocumentDTO getOneByIdCandidate(Long idCandidate) {
        // Todo : fix more than one document
        Candidate candidate = candidateRepository.findById(idCandidate).orElseThrow(() -> new RuntimeException("Candidate not found"));
        Optional<Document> document = documentRepository.findByCandidate(candidate);
        if (document.isPresent()) {
            return mapperDto.convertToDTO(document.get(), DocumentDTO.class);
        } else {
            throw new RuntimeException("Document not found");
        }
    }

    public DocumentDTO getOneDocument(Long idCandidate, String type) {
        Optional<Candidate> candidate = candidateRepository.findById(idCandidate);
        if (candidate.isPresent()) {
            Optional<Document> document = documentRepository.findByCandidateAndType(candidate.get(), type);
            if (document.isPresent()) {
                return mapperDto.convertToDTO(document.get(), DocumentDTO.class);
            } else {
                throw new RuntimeException("Document not found");
            }
        } else {
            throw new RuntimeException("Candidate not found");
        }
    }
}