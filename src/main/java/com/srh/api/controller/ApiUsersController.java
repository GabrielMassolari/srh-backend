package com.srh.api.controller;

import com.srh.api.dto.resource.ApiUserForm;
import com.srh.api.dto.resource.ApiUsersDto;
import com.srh.api.hypermedia.ApiUserModelAssembler;
import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import com.srh.api.service.ApiUserService;
import com.srh.api.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users/apis")
public class ApiUsersController {
    @Autowired
    private ApiUserService apiUserService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private ApiUserModelAssembler apiUserModelAssembler;

    @Autowired
    private PagedResourcesAssembler<ApiUsersDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ApiUsersDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                Pageable pageInfo) {
        Page<ApiUser> users = apiUserService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(ApiUsersDto.convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<ApiUsersDto> find(@PathVariable Integer id) {
        ApiUser apiUser = apiUserService.find(id);
        return apiUserModelAssembler.toModel(new ApiUsersDto(apiUser));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ApiUsersDto>> create(@RequestBody @Valid ApiUserForm apiUserForm,
                                                           UriComponentsBuilder uriBuilder) {
        ApiUser apiUser = apiUserForm.build();
        List<Profile> profiles = profileService.getProfilesByAuthority(apiUserForm.getIsAdmin());

        apiUser.setProfiles(profiles);
        apiUserService.save(apiUser);

        URI uri = uriBuilder.path("/users/apis/{id}").buildAndExpand(apiUser.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(apiUserModelAssembler.toModel(new ApiUsersDto(apiUser)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<ApiUsersDto> update(@RequestBody @Valid ApiUserForm apiUserForm,
                                           @PathVariable Integer id) {
        ApiUser apiUser = apiUserForm.build();
        List<Profile> profiles = profileService.getProfilesByAuthority(apiUserForm.getIsAdmin());

        apiUser.setId(id);
        apiUser.setProfiles(profiles);
        apiUser = apiUserService.update(apiUser, apiUserForm.getOldPassword());
        return apiUserModelAssembler.toModel(new ApiUsersDto(apiUser));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        apiUserService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
