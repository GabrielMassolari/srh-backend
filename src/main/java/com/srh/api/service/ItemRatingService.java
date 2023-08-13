package com.srh.api.service;

import com.srh.api.error.exception.RelationshipNotFoundException;
import com.srh.api.model.*;
import com.srh.api.repository.ItemRatingRepository;
import com.srh.api.repository.ItemRepository;
import com.srh.api.utils.PageUtil;
import lombok.Setter;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemRatingService {
    @Autowired
    private ItemRatingRepository itemRatingRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ProjectService projectService;

    @Setter
    private String authorizationHeaderValue;

    public ItemRating find(ItemRatingPK id) {
        Optional<ItemRating> itemRating = itemRatingRepository.findById(id);

        if (itemRating.isPresent())
            return itemRating.get();

        throw new ObjectNotFoundException(id.getClass(), ItemRating.class.getName());
    }

    public Page<ItemRating> findAll(Pageable pageInfo) {
        return itemRatingRepository.findAll(pageInfo);
    }

    public ItemRating save(ItemRating itemRating) {
        return itemRatingRepository.save(itemRating);
    }

    @SneakyThrows
    public ItemRating update(ItemRating itemRating, Integer evaluatorId, Integer itemId) {
        find(itemRating.getId());

        Boolean equalsEvaluator = itemRating.getId().getEvaluator().getId().equals(evaluatorId);
        Boolean equalsItem = itemRating.getId().getItem().getId().equals(itemId);

        if (equalsEvaluator && equalsItem) {
            return itemRatingRepository.save(itemRating);
        }

        throw new RelationshipNotFoundException("Invalid relationship");
    }

    public void delete(ItemRatingPK id) {
        ItemRating itemRating = find(id);
        itemRatingRepository.delete(itemRating);
    }

    public Page<ItemRating> listItensRatingsByItem(Integer itemId, Pageable pageInfo) {
        Item item = itemService.find(itemId);
        Item itemResult = itemService.find(item.getId());

        List<ItemRating> itensRatings = itemResult.getItemRatings();
        PageUtil<ItemRating> pageUtil = new PageUtil<>(pageInfo, itensRatings);
        return pageUtil.getPage();
    }

    public List<ItemRating> listItensRatingsByItem(Item item) {
        Item itemResult = itemService.find(item.getId());
        return itemResult.getItemRatings();
    }

    public Page<ItemRating> listItensRAtingsByTag(Integer tagId, Pageable pageInfo) {
        Tag tag = tagService.find(tagId);
        List<Item> itensWithTag = itemRepository.findByTags(tag);
        List<ItemRating> itensRatings = new ArrayList<>();

        for (Item item: itensWithTag) {
            itensRatings.addAll(item.getItemRatings());
        }

        PageUtil<ItemRating> pageUtil = new PageUtil<>(pageInfo, itensRatings);
        return pageUtil.getPage();
    }

    public Page<ItemRating> listItensRatingsByEvaluator(Integer evaluatorId, Pageable pageInfo) {
        Evaluator evaluator = evaluatorService.find(evaluatorId);
        List<ItemRating> itensRatings = evaluator.getItemRatings();
        PageUtil<ItemRating> pageUtil = new PageUtil<>(pageInfo, itensRatings);
        return pageUtil.getPage();
    }

    public Page<ItemRating> listItensRatingsByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Item> items = project.getItens();
        List<ItemRating> itemRatings = new ArrayList<>();

        for (Item item: items) {
            itemRatings.addAll(item.getItemRatings());
        }

        PageUtil<ItemRating> pageUtil = new PageUtil<>(pageInfo, itemRatings);
        return pageUtil.getPage();
    }
}
