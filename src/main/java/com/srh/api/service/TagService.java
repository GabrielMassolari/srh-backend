package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.Project;
import com.srh.api.model.Tag;
import com.srh.api.repository.TagRepository;
import com.srh.api.utils.PageUtil;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private ProjectService projectService;

    public Tag find(Integer id) {
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isPresent())
            return tag.get();

        throw new ObjectNotFoundException(id, Tag.class.getName());
    }

    public Page<Tag> findAll(Pageable pageInfo) {
        return tagRepository.findAll(pageInfo);
    }

    public List<Tag> findAll() {
        return (List<Tag>) tagRepository.findAll();
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(Tag tag) {
        find(tag.getId());
        return tagRepository.save(tag);
    }

    public void delete(Integer id) {
        find(id);
        tagRepository.deleteById(id);
    }

    public Page<Tag> listTagsByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Item> items = project.getItens();
        Map<Integer, Tag> tagsMap = new HashMap<>();

        for (Item item: items) {
            for (Tag tag: item.getTags()) {
                tagsMap.put(tag.getId(), tag);
            }
        }

        List<Tag> tags = new ArrayList<>(tagsMap.values());
        PageUtil<Tag> pageUtil = new PageUtil<>(pageInfo, tags);
        return pageUtil.getPage();
    }
}
