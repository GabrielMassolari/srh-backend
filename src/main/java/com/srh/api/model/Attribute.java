package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String value;
    private TypeAttribute type;

    @ManyToMany
    private List<Item> itens;

    @ManyToMany
    private List<TypeItem> typeItens;
}
