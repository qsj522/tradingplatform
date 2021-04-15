package com.qishengjie.tradingplatform.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductCategoryVO {
    private String id;
    private String name;
    private List<ProductCategoryVO> children;
    private String bannerImg;
    private String topImg;
    private List<ProductVO> productVOList;

    public ProductCategoryVO(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
