package com.srh.api.algorithms.resources.basedcontent;

import com.srh.api.model.Item;
import com.srh.api.model.Tag;
import com.srh.api.service.ItemTagService;
import com.srh.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ItemTagMatrix {
    @Autowired
    private ItemTagService itemTagService;

    @Autowired
    private TagService tagService;

    private Integer[][] content;
    private Integer colSize;
    private Integer rowSize;

    public void build(List<Item> items) {
        List<Tag> allTags = tagService.findAll();
        colSize = allTags.size();
        rowSize = items.size();

        content = new Integer[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                content[i][j] = getItemTagValue(items.get(i), allTags.get(j));
            }
        }
    }

    private Integer getItemTagValue(Item item, Tag tag) {
        return itemTagService.findTagByItemRecommendation(item, tag);
    }

    public Integer[][] getContent() {
        return content;
    }

    public Integer getColSize() {
        return colSize;
    }

    public Integer getRowSize() {
        return rowSize;
    }
}
