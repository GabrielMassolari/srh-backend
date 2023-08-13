package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemAttribute {
    private Item item;
    private Attribute attribute;

    @SneakyThrows
    public void addEntities() {
        addItemInAttribute();
        addAttributeInItem();
    }

    @SneakyThrows
    public void removeEntities() {
        removeItemInAttribute();
        removeAttributeInItem();
    }

    @SneakyThrows
    private void addItemInAttribute() {
        List<Item> itensInAttribute = getItensInAttribute();

        if (itensInAttribute.contains(item))
            throw new DuplicateValueException("Item link already exists");

        itensInAttribute.add(item);
    }

    @SneakyThrows
    private void addAttributeInItem() {
        List<Attribute> attributesInItem = getAttributesInItem();

        if (attributesInItem.contains(attribute))
            throw new DuplicateValueException("Attribute link already exists");

        attributesInItem.remove(attribute);
    }

    @SneakyThrows
    private void removeItemInAttribute() {
        List<Item> itensInAttribute = getItensInAttribute();

        if (!itensInAttribute.contains(item))
            throw new RelationshipNotFoundException("Item not exist in Attribute");

        itensInAttribute.remove(item);
    }

    @SneakyThrows
    private void removeAttributeInItem() {
        List<Attribute> attributesInItem = getAttributesInItem();

        if (!attributesInItem.contains(attribute))
            throw new RelationshipNotFoundException("Attribute not exist in Item");

        attributesInItem.remove(attribute);
    }

    private List<Item> getItensInAttribute() {
        return attribute.getItens();
    }

    private List<Attribute> getAttributesInItem() {
        return item.getAttributes();
    }
}
