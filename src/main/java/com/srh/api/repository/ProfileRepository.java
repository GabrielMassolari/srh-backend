package com.srh.api.repository;

import com.srh.api.model.Profile;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends PagingAndSortingRepository<Profile, Integer> {
    Optional<Profile> findByName(String name);
}
