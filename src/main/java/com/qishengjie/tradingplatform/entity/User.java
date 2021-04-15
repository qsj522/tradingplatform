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
 * 用户信息表
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Data
  @EqualsAndHashCode(callSuper = false)
  @Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用户id
     */
        private String userId;

      /**
     * 用户名（需包含英文字符和数字且长度不超过10为位）
     */
      private String loginName;

      /**
     * 昵称
     */
      private String nickname;

      /**
     * 密码
     */
      private String password;

      /**
     * 性别（1：男 2：女）
     */
      private Integer gender;

      /**
     * 身份证号码
     */
      private String identityCode;

      /**
     * 手机号码
     */
      private String mobile;

      /**
     * 信誉积分
     */
      private Integer creditScore;

      /**
     * 用户类型（0：管理员 1：用户）
     */
      private Integer type;

      /**
     * 用户头像
     */
      private String fileName;

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
