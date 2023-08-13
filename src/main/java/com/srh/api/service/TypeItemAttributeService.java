package com.srh.api.service;

import com.srh.api.model.Attribute;
import com.srh.api.model.TypeItem;
import com.srh.api.model.TypeItemAttribute;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeItemAttributeService {
    @Autowired
    private TypeItemService typeItemService;

    @Autowired
    private AttributeService attributeService;

    public Attribute findAttributeByTypeItem(Integer typeItemId, Integer attributeId) {
        TypeItem typeItem = typeItemService.find(typeItemId);
        Attribute attribute = attributeService.find(attributeId);

        if (typeItem.getAttributes().contains(attribute)) {
            return attribute;
        }

        throw new ObjectNotFoundException(attributeId, Attribute.class.getName());
    }

    public Page<Attribute> listAttributesByTypeItem(Integer typeItemId, Pageable pageInfo) {
        TypeItem typeItem = typeItemService.find(typeItemId);
        List<Attribute> attributes = typeItem.getAttributes();

        PageUtil<Attribute> pageUtil = new PageUtil<>(pageInfo, attributes);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public TypeItemAttribute save(Integer typeItemId, Integer attributeId) {
        TypeItem typeItem = typeItemService.find(typeItemId);
        Attribute attribute = attributeService.find(attributeId);

        TypeItemAttribute typeItemAttribute = new TypeItemAttribute(typeItem, attribute);
        typeItemAttribute.addEntities();
        persistEntities(typeItemAttribute);

        return typeItemAttribute;
    }

    @SneakyThrows
    public void delete(Integer typeItemId, Integer attributeId) {
        TypeItem typeItem = typeItemService.find(typeItemId);
        Attribute attribute = attributeService.find(attributeId);

        TypeItemAttribute typeItemAttribute = new TypeItemAttribute(typeItem, attribute);
        typeItemAttribute.removeEntities();
        persistEntities(typeItemAttribute);
    }


    public void persistEntities(TypeItemAttribute typeItemAttribute) {
        typeItemService.save(typeItemAttribute.getTypeItem());
        attributeService.save(typeItemAttribute.getAttribute());
    }
}
