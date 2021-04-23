package com.qishengjie.tradingplatform.vo;

import com.qishengjie.tradingplatform.entity.ProductCategory;
import lombok.Data;

import java.util.List;


@Data
public class LevelOneVO {
    private List<ProductCategory> productCategoryList;
}
