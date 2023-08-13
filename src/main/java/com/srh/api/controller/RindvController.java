package com.srh.api.controller;

import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
import com.srh.api.service.RindvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/rindvs")
public class RindvController {

    @Autowired
    private RindvService rindvService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{ProjectId}/{AlgorithmId}")
    public Double findARindv(@PathVariable Integer ProjectId, @PathVariable Integer AlgorithmId) {
        Project project = projectService.find(ProjectId);
        if (project.getId() == ProjectId) {
            Double rindv = rindvService.getRindv(ProjectId, AlgorithmId);
            return rindv;
        }
        return 0.0;
    }

}
