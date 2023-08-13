package com.srh.api.service;

import com.srh.api.model.Attribute;
import com.srh.api.repository.AttributeRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

    public Attribute find(Integer id) {
        Optional<Attribute> attribute = attributeRepository.findById(id);

        if (attribute.isPresent())
            return attribute.get();

        throw new ObjectNotFoundException(id, Attribute.class.getName());
    }

    public Page<Attribute> findAll(Pageable pageInfo) {
        return attributeRepository.findAll(pageInfo);
    }

    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }

    public Attribute update(Attribute attribute) {
        find(attribute.getId());
        return attributeRepository.save(attribute);
    }

    public void delete(Integer id) {
        find(id);
        attributeRepository.deleteById(id);
    }
}
