package com.qishengjie.tradingplatform.mapper;

import com.qishengjie.tradingplatform.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * <p>
 * 商品信息表 Mapper 接口
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface ProductMapper extends BaseMapper<Product> {
    Product selectByProductId(String id);
}
