package com.qishengjie.tradingplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Shengjie Qi
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultVO {
    //状态：0 成功 ，1 失败
    int status;
    //描述
    String desc;
    //地址
    String url;
}
