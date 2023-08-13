package com.srh.api.repository;

import com.srh.api.model.Admin;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AdminRepository extends PagingAndSortingRepository<Admin, Integer> {
    Optional<Admin> findByLogin(String login);
}
