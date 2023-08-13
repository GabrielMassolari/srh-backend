package com.srh.api.service;

import com.srh.api.error.exception.ChangeRootRelationException;
import com.srh.api.model.*;
import com.srh.api.repository.ProjectRepository;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AdminService adminService;

    public Project find(Integer id) {
        System.out.println(id);
        Optional<Project> project = projectRepository.findById(id);

        if (project.isPresent())
            return project.get();

        throw new ObjectNotFoundException(id, Project.class.getName());
    }

    public Page<Project> findAll(Pageable pageInfo) {
        return projectRepository.findAll(pageInfo);
    }

    public Project save(Project project) {
        Integer adminId = project.getAdmin().getId();
        Admin admin = adminService.find(adminId);


        project.setAdmin(admin);
        return projectRepository.save(project);
    }

    @SneakyThrows
    public Project update(Project project) {
        Project oldProject = find(project.getId());

        if (project.getAdmin().equals(oldProject.getAdmin())) {
            throw new ChangeRootRelationException("Correct value: " + oldProject.getAdmin().getId());
        }

        project.setItens(oldProject.getItens());
        return projectRepository.save(project);
    }

    public void delete(Integer id) {
        find(id);
        projectRepository.deleteById(id);
    }

    public List<Item> listItensByProject(Integer projectId) {
        Project project = find(projectId);
        return project.getItens();
    }

    public List<Recommendation> listRecommendationsByProject(Integer projectId) {
        List<Item> itens = listItensByProject(projectId);
        List<Recommendation> recommendations = new ArrayList<>();

        for (Item item : itens) {
            recommendations.addAll(item.getRecommendations());
        }

        return recommendations;
    }

    public List<ItemRating> listItemRatingsByProject(Integer projectId) {
        List<Item> itens = listItensByProject(projectId);
        List<ItemRating> itemRatings = new ArrayList<>();

        for (Item item : itens) {
            itemRatings.addAll(item.getItemRatings());
        }

        return itemRatings;
    }

    public List<Tag> listTagsByProject(Integer projectId) {
        List<Item> itens = listItensByProject(projectId);
        List<Tag> tags = new ArrayList<>();

        for (Item item : itens) {
            tags.addAll(item.getTags());
        }

        return tags;
    }

    public List<TypeItem> listTypeItensByProject(Integer projectId) {
        List<Item> itens = listItensByProject(projectId);
        List<TypeItem> typeItems = new ArrayList<>();

        for (Item item : itens) {
            if (item.getTypeItem() != null) {
                typeItems.add(item.getTypeItem());
            }
        }

        return typeItems;
    }

    public List<Evaluator> listEvaluatorsByProject(Integer projectId) {
        Project project = find(projectId);
        return project.getEvaluators();
    }

    public Project updateMatrixId(Integer projectId) {
        Project oldProject = find(projectId);
        oldProject.setLastMatrixId(oldProject.getLastMatrixId() + 1);
        return projectRepository.save(oldProject);
    }
}
