package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.entity.ProductCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qishengjie.tradingplatform.vo.LevelOneVO;
import com.qishengjie.tradingplatform.vo.ProductCategoryVO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 商品分类表 服务类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    ModelAndView list(HttpSession session);

     List<ProductCategoryVO> getAllProductCategoryVO();

    Object getLevelOne();

    Object getLevelTwo(String parentId);
}
