package com.bridgelabz.lmscandidateservice.service;

import com.bridgelabz.lmscandidateservice.dto.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;

import java.util.List;

public interface ICandidateService {
    CandidateModel addCandidate(CandidateDTO candidateDTO);

    CandidateModel updadateCandidate(Long id, String token, CandidateDTO candidateDTO);

    List<CandidateModel> getCandidates(String token);

    CandidateModel deleteCandidate(Long id, String token);

    CandidateModel getCandidate(Long id, String token);
}
