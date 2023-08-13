package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectDto;
import com.srh.api.hypermedia.ProjectModelAssembler;
import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
import com.srh.api.service.RpolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rpols")
public class RpolController {
    @Autowired
    private RpolService rpolService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{ProjectId}/{AlgorithmId}")
    public Double findARpol(@PathVariable Integer ProjectId, @PathVariable Integer AlgorithmId) {
        Project project = projectService.find(ProjectId);
        if (project.getId() == ProjectId) {
            Double rpol = rpolService.getRpol(ProjectId, AlgorithmId);
            return rpol;
        }
        return 0.0;
    }

}
