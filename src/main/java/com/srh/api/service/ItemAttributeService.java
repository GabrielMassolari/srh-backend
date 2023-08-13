package com.srh.api.service;

import com.srh.api.model.Attribute;
import com.srh.api.model.Item;
import com.srh.api.model.ItemAttribute;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemAttributeService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private AttributeService attributeService;

    public Attribute findAttributeByItem(Integer itemId, Integer attributeId) {
        Item item = itemService.find(itemId);
        Attribute attribute = attributeService.find(attributeId);

        if (item.getAttributes().contains(attribute)) {
            return attribute;
        }

        throw new ObjectNotFoundException(attributeId, Attribute.class.getName());
    }

    public Page<Attribute> listAttributesByItem(Integer itemId, Pageable pageInfo) {
        Item item = itemService.find(itemId);
        List<Attribute> attributes = item.getAttributes();

        PageUtil<Attribute> pageUtil = new PageUtil<>(pageInfo, attributes);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public ItemAttribute save(Integer itemId, Integer attributeId) {
        Item item = itemService.find(itemId);
        Attribute attribute = attributeService.find(attributeId);

        ItemAttribute itemAttribute = new ItemAttribute(item, attribute);
        itemAttribute.addEntities();
        persistEntities(itemAttribute);

        return itemAttribute;
    }

    public void delete(Integer itemId, Integer attributeId) {
        Item item = itemService.find(itemId);
        Attribute attribute = attributeService.find(attributeId);

        ItemAttribute itemAttribute = new ItemAttribute(item, attribute);
        itemAttribute.removeEntities();
        persistEntities(itemAttribute);
    }

    private void persistEntities(ItemAttribute itemAttribute) {
        itemService.save(itemAttribute.getItem());
        attributeService.save(itemAttribute.getAttribute());
    }
}
