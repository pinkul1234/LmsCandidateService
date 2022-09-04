package com.bridgelabz.lmscandidateservice.controller;

import com.bridgelabz.lmscandidateservice.dto.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.service.ICandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    ICandidateService candidateService;

    @PostMapping("/addcandidate")
    public CandidateModel addCandidate( @Valid @RequestBody CandidateDTO candidateDTO){
        return candidateService.addCandidate(candidateDTO);
    }

    @PutMapping("update/{id}")
    public CandidateModel updateCandidate(@RequestHeader String token, @Valid @RequestBody CandidateDTO candidateDTO, @PathVariable Long id){
        return candidateService.updadateCandidate(id, token, candidateDTO);
    }

    @GetMapping("/getcandidates")
    public List<CandidateModel> getCandidates(@RequestHeader String token) {
        return candidateService.getCandidates(token);
    }

    @DeleteMapping("deletecandidate/{id}")
    public CandidateModel deleteCandidate(@PathVariable Long id, @RequestHeader String token) {
        return candidateService.deleteCandidate(id, token);
    }

    @GetMapping("getcandidate/{id}")
    public CandidateModel getCandidate(@PathVariable Long id, @RequestHeader String token) {
        return candidateService.getCandidate(id, token);
    }







}
