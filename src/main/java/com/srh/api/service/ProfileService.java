package com.srh.api.service;

import com.srh.api.model.Profile;
import com.srh.api.repository.ProfileRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile find(Integer id) {
        Optional<Profile> profile = profileRepository.findById(id);

        if (profile.isPresent())
            return profile.get();

        throw new ObjectNotFoundException(id, Profile.class.getName());
    }

    public Page<Profile> findAll(Pageable pageInfo) {
        return profileRepository.findAll(pageInfo);
    }

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile update(Profile profile) {
        find(profile.getId());
        return profileRepository.save(profile);
    }

    public void delete(Integer id) {
        find(id);
        profileRepository.deleteById(id);
    }

    public List<Profile> getProfilesByAuthority(boolean isAdmin) {
        if (isAdmin) {
            return (List<Profile>) profileRepository.findAll();
        }
        return Collections.singletonList(find(2));
    }
}
