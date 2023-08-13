package com.srh.api.service;

import com.srh.api.model.ApiUser;
import com.srh.api.model.ApiUserProfile;
import com.srh.api.model.Profile;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiUserProfileService {
    @Autowired
    private ApiUserService apiUserService;

    @Autowired
    private ProfileService profileService;

    public Profile findProfileByApiUser(Integer apiUserId, Integer profileId) {
        ApiUser apiUser = apiUserService.find(apiUserId);
        Profile profile = profileService.find(profileId);

        if (apiUser.getProfiles().contains(profile)) {
            return profile;
        }

        throw new ObjectNotFoundException(profileId, Profile.class.getName());
    }

    public Page<Profile> listProfilesByApiUser(Integer apiUserId, Pageable pageInfo) {
        ApiUser apiUser = apiUserService.find(apiUserId);
        List<Profile> profiles = apiUser.getProfiles();

        PageUtil<Profile> pageUtil = new PageUtil<>(pageInfo, profiles);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public ApiUserProfile save(Integer apiUserId, Integer profileId) {
        ApiUser apiUser = apiUserService.find(apiUserId);
        Profile profile = profileService.find(profileId);

        ApiUserProfile apiUserProfile = new ApiUserProfile(apiUser, profile);
        apiUserProfile.addEntities();
        persistEntities(apiUserProfile);

        return apiUserProfile;
    }

    @SneakyThrows
    public void delete(Integer apiUserId, Integer profileId) {
        ApiUser apiUser = apiUserService.find(apiUserId);
        Profile profile = profileService.find(profileId);

        ApiUserProfile apiUserProfile = new ApiUserProfile(apiUser, profile);
        apiUserProfile.removeEntities();
        persistEntities(apiUserProfile);
    }

    private void persistEntities(ApiUserProfile apiUserProfile) {
        apiUserService.save(apiUserProfile.getApiUser());
        profileService.save(apiUserProfile.getProfile());
    }
}
