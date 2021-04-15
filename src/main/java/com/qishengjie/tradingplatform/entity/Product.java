package com.qishengjie.tradingplatform.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商品信息表
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 商品id
     */
        private String productId;

      /**
     * 发布用户id
     */
      private String userId;

      /**
     * 商品名称
     */
      private String name;

      /**
     * 商品描述
     */
      private String description;

      /**
     * 发布价格
     */
      private Integer price;

      /**
     * 是否正在交易（0：未交易 1：交易中）
     */
      private Integer isTrading;

      /**
     * 一级分类id
     */
      @TableField("Category_level_one_id")
    private String categoryLevelOneId;

      /**
     * 二级分类id
     */
      @TableField("Category_level_two_id")
    private String categoryLevelTwoId;

      /**
     * 三级分类id
     */
      @TableField("Category_level_three_id")
    private String categoryLevelThreeId;

      /**
     * 商品图片
     */
      private String fileName;


}
