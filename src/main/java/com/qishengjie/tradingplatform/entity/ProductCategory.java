package com.qishengjie.tradingplatform.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品分类表
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class ProductCategory implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 商品分类id
     */
        private String id;

      /**
     * 类别名称
     */
      private String name;

      /**
     * 父级分类id
     */
      private String parentId;

      /**
     * 类别（1：一级分类 2：二级分类 3：三级分类）
     */
      private Integer type;


}
