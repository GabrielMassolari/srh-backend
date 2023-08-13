package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.Project;
import com.srh.api.model.TypeItem;
import com.srh.api.repository.TypeItemRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TypeItemService {
    @Autowired
    private TypeItemRepository typeItemRepository;

    @Autowired
    private ProjectService projectService;

    public TypeItem find(Integer id) {
        Optional<TypeItem> typeItem = typeItemRepository.findById(id);

        if (typeItem.isPresent())
            return typeItem.get();

        throw new ObjectNotFoundException(id, TypeItem.class.getName());
    }

    public Page<TypeItem> findAll(Pageable pageInfo) {
        return typeItemRepository.findAll(pageInfo);
    }

    public TypeItem save(TypeItem typeItem) {
        return typeItemRepository.save(typeItem);
    }

    public TypeItem update(TypeItem typeItem) {
        find(typeItem.getId());
        return typeItemRepository.save(typeItem);
    }

    public void delete(Integer id) {
        find(id);
        typeItemRepository.deleteById(id);
    }

    public List<TypeItem> listByProject(Integer projectId) {
        Project project = projectService.find(projectId);
        List<Item> items = project.getItens();
        Map<Integer, TypeItem> typeItemMap = new HashMap<>();

        for(Item item: items) {
            TypeItem typeItem = item.getTypeItem();

            if (typeItem != null)
                typeItemMap.put(typeItem.getId(), typeItem);
        }

        return new ArrayList<>(typeItemMap.values());
    }
}
