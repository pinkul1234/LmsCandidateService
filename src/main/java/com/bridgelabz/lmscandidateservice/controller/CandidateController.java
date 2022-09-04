package com.bridgelabz.lmscandidateservice.controller;

import com.bridgelabz.lmscandidateservice.dto.CandidateDTO;
import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import com.bridgelabz.lmscandidateservice.service.ICandidateService;
import com.bridgelabz.lmscandidateservice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    ICandidateService candidateService;

    @PostMapping("/addcandidate")
    public ResponseEntity<Response> addCandidate(@RequestBody CandidateDTO candidateDTO, @RequestHeader String token){
        Response response = candidateService.addCandidate(candidateDTO, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Response> updateCandidate(@RequestHeader String token, @RequestBody CandidateDTO candidateDTO, @PathVariable Long id){
        Response response = candidateService.updadateCandidate(id, token, candidateDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getcandidates")
    public ResponseEntity<List<?>> getCandidates(@RequestHeader String token) {
        List<CandidateModel> response = candidateService.getCandidates(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("deletecandidate/{id}")
    public ResponseEntity<Response> deleteCandidate(@PathVariable Long id, @RequestHeader String token) {
        Response response = candidateService.deleteCandidate(id, token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/changestatus")
    public ResponseEntity<Response> changeStatus(@RequestHeader String token, @PathVariable long id, @RequestParam String newStatus){
        Response response = candidateService.changeStatus(token, id, newStatus);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
