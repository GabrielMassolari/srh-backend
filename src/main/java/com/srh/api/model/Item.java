package com.srh.api.model;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "id.item", cascade = CascadeType.ALL, orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ItemRating> itemRatings;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recommendation> recommendations;

    @ManyToOne
    private Project project;

    @ManyToOne
    private TypeItem typeItem;

    @ManyToMany(mappedBy = "itens")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Tag> tags;

    @ManyToMany(mappedBy = "itens")
    private List<Attribute> attributes;
}
