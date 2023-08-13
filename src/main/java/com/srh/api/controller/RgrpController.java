package com.srh.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srh.api.model.Project;
import com.srh.api.service.ProjectService;
import com.srh.api.service.RgrpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rgrps")
public class RgrpController {

    @Autowired
    private RgrpService rgrpService;

    @Autowired
    private ProjectService projectService;

    @PostMapping("/test")
    public Double findARindv(@RequestBody JsonNode requestBody) {
        Integer projectId = requestBody.get("ProjectId").asInt();
        Integer algorithmId = requestBody.get("AlgorithmId").asInt();
        ArrayList<Double>[] groups = new ObjectMapper().convertValue(requestBody.get("Groups"), ArrayList[].class);

        Project project = projectService.find(projectId);
        if (project.getId().equals(projectId)) {
            Double rgrp = rgrpService.getRgrp(projectId, algorithmId, groups);
            return rgrp;
        }
        return 0.0;
    }

}
