package com.srh.api.repository;

import com.srh.api.model.ApiUser;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ApiUserRepository extends PagingAndSortingRepository<ApiUser, Integer> {
    Optional<ApiUser> findByLogin(String login);
}
