package com.srh.api.service;

import com.srh.api.model.ApiUser;
import com.srh.api.repository.ApiUserRepository;
import com.srh.api.utils.PasswordUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiUserService {
    @Autowired
    private ApiUserRepository apiUserRepository;

    private PasswordUtil<ApiUser> passwordUtil = new PasswordUtil<>();

    public ApiUser find(Integer id) {
        Optional<ApiUser> userApi = apiUserRepository.findById(id);

        if (userApi.isPresent())
            return userApi.get();

        throw new ObjectNotFoundException(id, ApiUser.class.getName());
    }

    public Page<ApiUser> findAll(Pageable pageInfo) {
        return apiUserRepository.findAll(pageInfo);
    }

    public ApiUser save(ApiUser apiUser) {
        ApiUser apiUserEncoded = passwordUtil.encodedPasswordForUser(apiUser);
        return apiUserRepository.save(apiUserEncoded);
    }

    public ApiUser update(ApiUser apiUser, String oldPasswordRaw) {
        ApiUser oldApiUser = find(apiUser.getId());
        ApiUser persistApiUser = passwordUtil.verifyPasswordChanges(apiUser, oldApiUser, oldPasswordRaw);
        return apiUserRepository.save(persistApiUser);
    }

    public void delete(Integer id) {
        find(id);
        apiUserRepository.deleteById(id);
    }
}
