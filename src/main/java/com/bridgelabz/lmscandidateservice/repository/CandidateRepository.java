package com.bridgelabz.lmscandidateservice.repository;

import com.bridgelabz.lmscandidateservice.model.CandidateModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<CandidateModel, Long> {
}
