package com.qishengjie.tradingplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qishengjie.tradingplatform.entity.Product;
import com.qishengjie.tradingplatform.entity.ProductCategory;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.mapper.ProductCategoryMapper;
import com.qishengjie.tradingplatform.mapper.ProductMapper;
import com.qishengjie.tradingplatform.service.ProductCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishengjie.tradingplatform.vo.ProductCategoryVO;
import com.qishengjie.tradingplatform.vo.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品分类表 服务实现类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductMapper productMapper;

    @Override
    public ModelAndView list(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        modelAndView.addObject("list",getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
//            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @Override
    public List<ProductCategoryVO> getAllProductCategoryVO() {
        //一级分类
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("type",1);
        List<ProductCategory> levelOne = productCategoryMapper.selectList(wrapper);
        List<ProductCategoryVO> levelOneVO = levelOne.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
        //图片赋值
        //商品信息赋值
        for (int i = 0; i < levelOneVO.size(); i++) {
            levelOneVO.get(i).setBannerImg("/images/banner"+i+".png");
            levelOneVO.get(i).setTopImg("/images/top"+i+".png");
            wrapper = new QueryWrapper();
            wrapper.eq("Category_level_one_id",levelOneVO.get(i).getId());
            List<Product> productList = productMapper.selectList(wrapper);
            List<ProductVO> productVOList = productList.stream()
                    .map(e -> new ProductVO(
                            e.getProductId(),
                            e.getName(),
                            e.getPrice(),
                            e.getFileName()
                    )).collect(Collectors.toList());
            levelOneVO.get(i).setProductVOList(productVOList);
        }
        for (ProductCategoryVO levelOneProductCategoryVO : levelOneVO) {
            wrapper = new QueryWrapper();
            wrapper.eq("type",2);
            wrapper.eq("parent_id",levelOneProductCategoryVO.getId());
            List<ProductCategory> levelTwo = productCategoryMapper.selectList(wrapper);
            List<ProductCategoryVO> levelTwoVO = levelTwo.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
            levelOneProductCategoryVO.setChildren(levelTwoVO);
            for (ProductCategoryVO levelTwoProductCategoryVO : levelTwoVO) {
                wrapper = new QueryWrapper();
                wrapper.eq("type",3);
                wrapper.eq("parent_id",levelTwoProductCategoryVO.getId());
                List<ProductCategory> levelThree = productCategoryMapper.selectList(wrapper);
                List<ProductCategoryVO> levelThreeVO = levelThree.stream().map(e -> new ProductCategoryVO(e.getId(),e.getName())).collect(Collectors.toList());
                levelTwoProductCategoryVO.setChildren(levelThreeVO);
            }
        }
        return levelOneVO;
    }
}
