package com.srh.api.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;
    private LocalDate date;
    private Boolean visible;
    private Integer lastMatrixId;

    @Enumerated(EnumType.STRING)
    private Situations situation;

    @ManyToOne
    private Admin admin;

    @ManyToMany(mappedBy = "projects")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Evaluator> evaluators;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Item> itens;
}
