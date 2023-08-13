package com.srh.api.service;

import com.srh.api.model.Item;
import com.srh.api.model.ItemTag;
import com.srh.api.model.Tag;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemTagService {
    @Autowired
    private ItemService itemService;

    @Autowired
    private TagService tagService;

    public Tag findTagByItem(Integer itemId, Integer tagId) {
        Item item = itemService.find(itemId);
        Tag tag = tagService.find(tagId);

        if (item.getTags().contains(tag)) {
            return tag;
        }

        throw new ObjectNotFoundException(tagId, Tag.class.getName());
    }

    public Integer findTagByItemRecommendation(Item item, Tag tag) {
        if (item.getTags().contains(tag)) {
            return 1;
        }
        return 0;
    }

    public Page<Tag> listTagsByItem(Integer itemId, Pageable pageInfo) {
        Item item = itemService.find(itemId);
        List<Tag> tags = item.getTags();

        PageUtil<Tag> pageUtil = new PageUtil<>(pageInfo, tags);
        return pageUtil.getPage();
    }

    public List<Tag> listTagsByItem(Integer itemId) {
        Item item = itemService.find(itemId);
        return item.getTags();
    }

    @SneakyThrows
    public ItemTag save(Integer itemId, Integer tagId) {
        Item item = itemService.find(itemId);
        Tag tag = tagService.find(tagId);

        ItemTag itemTag = new ItemTag(item, tag);
        itemTag.addEntities();
        persistEntities(itemTag);

        return itemTag;
    }

    @SneakyThrows
    public void delete(Integer itemId, Integer tagId) {
        Item item = itemService.find(itemId);
        Tag tag = tagService.find(tagId);

        ItemTag itemTag = new ItemTag(item, tag);
        itemTag.removeEntities();
        persistEntities(itemTag);
    }

    private void persistEntities(ItemTag itemTag) {
        itemService.save(itemTag.getItem());
        tagService.save(itemTag.getTag());
    }
}
