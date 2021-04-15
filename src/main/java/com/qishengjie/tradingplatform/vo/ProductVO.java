package com.qishengjie.tradingplatform.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductVO {
    private String id;
    private String name;
    private int price;
    private String fileName;
}
