package com.srh.api.repository;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface EvaluatorRepository extends PagingAndSortingRepository<Evaluator, Integer> {
    Optional<Evaluator> findByLogin(String login);
}
