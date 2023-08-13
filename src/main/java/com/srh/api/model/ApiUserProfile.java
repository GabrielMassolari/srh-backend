package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
public class ApiUserProfile {
    private ApiUser apiUser;
    private Profile profile;

    public ApiUserProfile(ApiUser apiUser, Profile profile) {
        this.apiUser = apiUser;
        this.profile = profile;
    }

    @SneakyThrows
    public void addEntities() {
        addApiUserInProfile();
        addProfileInApiUser();
    }

    @SneakyThrows
    public void removeEntities() {
        removeApiUserInProfile();
        removeProfileInApiUser();
    }

    @SneakyThrows
    private void addApiUserInProfile() {
        List<ApiUser> apiUsersInProfile = getApiUserListInProfile();

        if (apiUsersInProfile.contains(apiUser))
            throw new DuplicateValueException("ApiUser link already exists");

        apiUsersInProfile.add(apiUser);
    }

    @SneakyThrows
    private void addProfileInApiUser() {
        List<Profile> profilesInApiUser = getProfileListInApiUser();

        if (profilesInApiUser.contains(profile))
            throw new DuplicateValueException("Profile link already exists");

        profilesInApiUser.add(profile);
    }

    @SneakyThrows
    private void removeApiUserInProfile() {
        List<ApiUser> apiUsersInProfile = profile.getApiUsers();

        if (!apiUsersInProfile.contains(apiUser))
            throw new RelationshipNotFoundException("ApiUser not exist in Profile");

        apiUsersInProfile.remove(apiUser);
    }

    @SneakyThrows
    private void removeProfileInApiUser() {
        List<Profile> profilesInApiUser = apiUser.getProfiles();

        if (!profilesInApiUser.contains(profile))
            throw new RelationshipNotFoundException("Profile not exist in ApiUser");

        profilesInApiUser.remove(profile);
    }

    private List<ApiUser> getApiUserListInProfile() {
        return profile.getApiUsers();
    }

    private List<Profile> getProfileListInApiUser() {
        return apiUser.getProfiles();
    }
}
