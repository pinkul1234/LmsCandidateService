package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.dto.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.util.Response;

import java.util.List;

public interface ICandidateService {
    Response addCandidate(CandidateDTO candidateDTO, String token);

    Response updadateCandidate(Long id, String token, CandidateDTO candidateDTO);

    List<CandidateModel> getCandidates(String token);

    Response deleteCandidate(Long id, String token);


    Response changeStatus(String token, long id, String newStatus);
}
