package com.srh.api.repository;

import com.srh.api.model.Project;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Integer> {
    Optional<Project> findTopByOrderByIdDesc();
}
