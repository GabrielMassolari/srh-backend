package com.srh.api.service;

import com.srh.api.model.Algorithm;
import com.srh.api.repository.AlgorithmRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlgorithmService {
    @Autowired
    private AlgorithmRepository algorithmRepository;

    public Algorithm find(Integer id) {
        Optional<Algorithm> algorithm = algorithmRepository.findById(id);

        if (algorithm.isPresent())
            return algorithm.get();

        throw new ObjectNotFoundException(id, Algorithm.class.getName());
    }

    public Page<Algorithm> findAll(Pageable pageInfo) {
        return algorithmRepository.findAll(pageInfo);
    }

    public Algorithm save(Algorithm algorithm) {
        return algorithmRepository.save(algorithm);
    }

    public Algorithm update(Algorithm algorithm) {
        find(algorithm.getId());
        return algorithmRepository.save(algorithm);
    }

    public void delete(Integer id) {
        find(id);
        algorithmRepository.deleteById(id);
    }
}
