package com.qishengjie.tradingplatform.entity;

import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class UserAddress implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 地址id
     */
        private String addressId;

      /**
     * 用户id
     */
      private String userId;

      /**
     * 地址描述
     */
      private String addressDesc;

      /**
     * 标记（如家、公司等）
     */
      private String remark;

      /**
     * 是否默认（0：是 1：否）
     */
      private Integer isDefault;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createdTime;

      /**
     * 修改时间
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime modifiedTime;


}
