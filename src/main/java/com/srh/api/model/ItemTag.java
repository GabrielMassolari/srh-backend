package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemTag {
    private Item item;
    private Tag tag;

    @SneakyThrows
    public void addEntities() {
        addItemInTag();
        addTagInItem();
    }

    @SneakyThrows
    public void removeEntities() {
        removeItemInTag();
        removeTagInItem();
    }

    @SneakyThrows
    private void addItemInTag() {
        List<Item> itensInTag = getItensListInTag();

        if (itensInTag.contains(item))
            throw new DuplicateValueException("Item link already exists");

        itensInTag.add(item);
    }

    @SneakyThrows
    private void addTagInItem() {
        List<Tag> tagsInItem = getTagListInItem();

        if (tagsInItem.contains(tag))
            throw new DuplicateValueException("Tag link already exists");

        tagsInItem.add(tag);
    }

    @SneakyThrows
    private void removeItemInTag() {
        List<Item> itensInTag = getItensListInTag();

        if (!itensInTag.contains(item))
            throw new RelationshipNotFoundException("Item not exist in Evaluator");

        itensInTag.remove(item);
    }

    @SneakyThrows
    private void removeTagInItem() {
        List<Tag> tagsInItem = getTagListInItem();

        if (!tagsInItem.contains(tag))
            throw new RelationshipNotFoundException("Tag not exist in Evaluator");

        tagsInItem.remove(tag);
    }

    private List<Item> getItensListInTag() {
        return tag.getItens();
    }

    private List<Tag> getTagListInItem() {
        return item.getTags();
    }
}
