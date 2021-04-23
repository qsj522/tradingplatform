package com.qishengjie.tradingplatform.mapper;

import com.qishengjie.tradingplatform.entity.ProductCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qishengjie.tradingplatform.vo.LevelOneVO;

import java.util.List;

/**
 * <p>
 * 商品分类表 Mapper 接口
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface ProductCategoryMapper extends BaseMapper<ProductCategory> {
    List<ProductCategory> getLevelOne();

    List<ProductCategory> getLevelTwo(String parentId);
}
