package com.srh.api.utils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PageUtil<T> {
    private final Pageable pageInfo;
    private final List<T> itens;

    public PageUtil(Pageable pageInfo, List<T> itens) {
        this.pageInfo = pageInfo;
        this.itens = itens;
    }

    public Page<T> getPage() {
        return new PageImpl<>(generateSublist(), pageInfo, itens.size());
    }

    private List<T> generateSublist() {
        return itens.subList(getFirstIndex(), calcToIndex());
    }

    private Integer calcToIndex() {
        Integer listFinalIndex = itens.size();
        Integer finalIndex = getFirstIndex() + pageInfo.getPageSize();

        if (finalIndex > listFinalIndex) {
            return listFinalIndex;
        }
        return finalIndex;
    }

    private Integer getFirstIndex() {
        return pageInfo.getPageNumber() * pageInfo.getPageSize();
    }
}
