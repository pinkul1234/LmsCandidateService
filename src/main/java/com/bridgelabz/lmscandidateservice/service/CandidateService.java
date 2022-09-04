package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.dto.CandidateDTO;
import com.bridgelabz.lmscandidateservice.exception.CandidateNotFoundException;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.repository.CandidateRepository;
import com.bridgelabz.lmscandidateservice.util.Response;
import com.bridgelabz.lmscandidateservice.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService implements ICandidateService {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    MailService mailService;

    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public Response addCandidate(CandidateDTO candidateDTO, String token) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isCandidatePresent) {
            CandidateModel candidateModel = new CandidateModel(candidateDTO);
            candidateModel.setCreationTimeStamp(LocalDateTime.now());
            candidateRepository.save(candidateModel);
            String body = "Candidate added sucessfully " + candidateModel.getId();
            String subject = "CandidateS registration completed";
            mailService.send(candidateModel.getEmail(), body, subject);
            return new Response("Success", 200, candidateModel);
        }
        throw new CandidateNotFoundException(400, "Not found");
    }

    @Override
    public Response updadateCandidate(Long id, String token, CandidateDTO candidateDTO) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isCandidatePresent) {
            Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(id);
            if (isCandidateAvailable.isPresent()) {
                isCandidateAvailable.get().setFullName(candidateDTO.getFullName());
                isCandidateAvailable.get().setEmail(candidateDTO.getEmail());
                isCandidateAvailable.get().setHiredDate(candidateDTO.getHiredDate());
                isCandidateAvailable.get().setHiredDate(candidateDTO.getHiredDate());
                isCandidateAvailable.get().setDegree(candidateDTO.getDegree());
                isCandidateAvailable.get().setAggrPer(candidateDTO.getAggrPer());
                isCandidateAvailable.get().setCity(candidateDTO.getCity());
                isCandidateAvailable.get().setState(candidateDTO.getState());
                isCandidateAvailable.get().setPreferredJobLocation(candidateDTO.getPreferredJobLocation());
                isCandidateAvailable.get().setPassedOutYear(candidateDTO.getPassedOutYear());
                candidateRepository.save(isCandidateAvailable.get());
                String body = "candidate is added successfully with adminId " + isCandidateAvailable.get().getId();
                String subject = "Candidate registration successful";
                mailService.send(isCandidateAvailable.get().getEmail(), subject, body);
                return new Response("success", 200, isCandidateAvailable.get());
            } else {
                throw new CandidateNotFoundException(400, "Candidate not availale");
            }
        }
        throw new CandidateNotFoundException(400, "token is wrong");
    }

    @Override
    public List<CandidateModel> getCandidates(String token) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isCandidatePresent) {
            List<CandidateModel> isCandidate = candidateRepository.findAll();
            if (isCandidate.size() > 0) {
                return (isCandidate);
            } else {
                throw new CandidateNotFoundException(400, "No candidate is present ");
            }
        }
        throw new CandidateNotFoundException(400, "Token is wrong ");
    }

    @Override
    public Response deleteCandidate(Long id, String token) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isCandidatePresent) {
            Optional<CandidateModel> isCandidateAvailable = candidateRepository.findById(id);
            if (isCandidateAvailable.isPresent()) {
                candidateRepository.delete(isCandidateAvailable.get());
                return new Response("Success", 200, isCandidateAvailable);
            } else {
                throw new  CandidateNotFoundException(400, "Candidate not found");
            }
        }
        throw new CandidateNotFoundException(400, "Token is wrong");
    }


    @Override
    public Response changeStatus(String token, long id, String newStatus) {
        boolean isCandidatePresent = restTemplate.getForObject("http://localhost:8082/admin/validate/" + token, Boolean.class);
        if (isCandidatePresent) {
            Optional<CandidateModel> isCandidate = candidateRepository.findById(id);
            if (isCandidate.isPresent()) {
                isCandidate.get().setStatus(newStatus);
                candidateRepository.save(isCandidate.get());
                return new Response("success", 200, isCandidate);
            }
            throw new CandidateNotFoundException(400, "Candidate not present");
        }
        throw new CandidateNotFoundException(400, "Token Wrong");

    }
}
