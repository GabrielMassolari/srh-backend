package com.srh.api.hypermedia;

import com.srh.api.controller.ApiUsersController;
import com.srh.api.dto.resource.ApiUsersDto;
import com.srh.api.model.Profile;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ApiUserModelAssembler implements RepresentationModelAssembler<ApiUsersDto, EntityModel<ApiUsersDto>> {
    @Override
    public EntityModel<ApiUsersDto> toModel(ApiUsersDto apiUsersDto) {
        EntityModel<ApiUsersDto> apiUsersEntityModel = new EntityModel<>(apiUsersDto,
                linkTo(methodOn(ApiUsersController.class).find(apiUsersDto.getId())).withSelfRel(),
                linkTo(ApiUsersController.class).withRel("apis")
        );

        if (apiUsersDto.getProfiles() != null) {
            apiUsersEntityModel.add(buildProfilesLinks(apiUsersDto.getProfiles()));
        }

        return apiUsersEntityModel;
    }

    private List<Link> buildProfilesLinks(List<Profile> profiles) {
        List<Link> links = new ArrayList<>();

        for (Profile profile : profiles) {
            links.add(linkTo(methodOn(ApiUsersController.class).find(profile.getId())).withRel("profiles"));
        }

        return links;
    }
}
