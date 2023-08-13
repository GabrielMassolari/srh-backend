package com.srh.api.builder;

import com.srh.api.model.*;

import java.time.LocalDate;
import java.util.List;

public final class ProjectBuilder {
    private Integer id;
    private String name;
    private String description;
    private LocalDate date;
    private Boolean visible;
    private Integer lastMatrixId;
    private Situations situation;
    private Admin admin;
    private List<Evaluator> evaluators;
    private List<Item> itens;

    private ProjectBuilder() {
    }

    public static ProjectBuilder aProject() {
        return new ProjectBuilder();
    }

    public ProjectBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProjectBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProjectBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProjectBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ProjectBuilder withVisible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public ProjectBuilder withLastMatrixId(Integer lastMatrixId) {
        this.lastMatrixId = lastMatrixId;
        return this;
    }

    public ProjectBuilder withSituation(Situations situation) {
        this.situation = situation;
        return this;
    }

    public ProjectBuilder withAdmin(Admin admin) {
        this.admin = admin;
        return this;
    }

    public ProjectBuilder withEvaluators(List<Evaluator> evaluators) {
        this.evaluators = evaluators;
        return this;
    }

    public ProjectBuilder withItens(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public Project build() {
        Project project = new Project();
        project.setId(id);
        project.setName(name);
        project.setDescription(description);
        project.setDate(date);
        project.setVisible(visible);
        project.setLastMatrixId(lastMatrixId);
        project.setSituation(situation);
        project.setAdmin(admin);
        project.setEvaluators(evaluators);
        project.setItens(itens);
        return project;
    }
}
