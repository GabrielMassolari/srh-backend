package com.srh.api.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srh.api.model.Project;
import com.srh.api.service.FairnessRecommendationService;
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

    @Autowired
    private FairnessRecommendationService fairnessRecommendationService;

    @PostMapping("/test")
    public Double findARindv(@RequestBody JsonNode requestBody) {
        Integer projectId = requestBody.get("ProjectId").asInt();
        Integer algorithmId = requestBody.get("AlgorithmId").asInt();
        ArrayList<Integer>[] groups = new ObjectMapper().convertValue(requestBody.get("Groups"), ArrayList[].class);

        Project project = projectService.find(projectId);
        if (project.getId().equals(projectId)) {
            Double rgrp = rgrpService.getRgrp(projectId, algorithmId, groups);
            return rgrp;
        }
        return 0.0;
    }

    @PostMapping("/getx1")
    public double[][] genx1(@RequestBody JsonNode requestBody) {
        Integer projectId = requestBody.get("ProjectId").asInt();
        Integer algorithmId = requestBody.get("AlgorithmId").asInt();
        Integer listNumber = requestBody.get("ListNumber").asInt();
        ArrayList<Integer>[] groups = new ObjectMapper().convertValue(requestBody.get("Groups"), ArrayList[].class);

        Project project = projectService.find(projectId);
        double[][] x1 = fairnessRecommendationService.getFairnessRecomendation(projectId, algorithmId, listNumber, groups);
        return x1;
    }

}
