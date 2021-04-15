package com.qishengjie.tradingplatform.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


/**
 * @Package: com.qishengjie.tradingplatform.mapper
 * @ClassName: ProductCategoryMapperTest
 * @Author: SamSung
 * @CreateTime: 2021-04-10 16:37
 * @Description:
 */
@SpringBootTest
class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    @Transactional
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }

}