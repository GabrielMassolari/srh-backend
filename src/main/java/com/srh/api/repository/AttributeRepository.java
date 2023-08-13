package com.srh.api.repository;

import com.srh.api.model.Attribute;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttributeRepository extends PagingAndSortingRepository<Attribute, Integer> {
}
