package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
public class TypeItemAttribute {
    private TypeItem typeItem;
    private Attribute attribute;

    @SneakyThrows
    public void addEntities() {
        addTypeItemInAttribute();
        addAttributeInTypeItem();
    }

    @SneakyThrows
    public void removeEntities() {
        removeAttributeInTypeItem();
        removeTypeItemInAttribute();
    }

    @SneakyThrows
    private void addTypeItemInAttribute() {
        List<TypeItem> typeItensInAttribute = getTypeItensInAttribute();

        if (typeItensInAttribute.contains(typeItem))
            throw new DuplicateValueException("TypeItem link already exists");

        typeItensInAttribute.add(typeItem);
    }

    @SneakyThrows
    private void addAttributeInTypeItem() {
        List<Attribute> attributesInTypeItem = getAttributesInTypeItem();

        if (attributesInTypeItem.contains(attribute))
            throw new DuplicateValueException("Attribute link already exists");

        attributesInTypeItem.add(attribute);
    }

    @SneakyThrows
    private void removeTypeItemInAttribute() {
        List<TypeItem> typeItensInAttribute = getTypeItensInAttribute();

        if (!typeItensInAttribute.contains(typeItem))
            throw new RelationshipNotFoundException("TypeItem not exist in Attribute");

        typeItensInAttribute.remove(typeItem);
    }

    @SneakyThrows
    private void removeAttributeInTypeItem() {
        List<Attribute> attributesInTypeItem = getAttributesInTypeItem();

        if (!attributesInTypeItem.contains(attribute))
            throw new RelationshipNotFoundException("Attribute not exist in TypeItem");

        attributesInTypeItem.remove(attribute);
    }

    private List<TypeItem> getTypeItensInAttribute() {
        return attribute.getTypeItens();
    }

    private List<Attribute> getAttributesInTypeItem() {
        return typeItem.getAttributes();
    }
}
