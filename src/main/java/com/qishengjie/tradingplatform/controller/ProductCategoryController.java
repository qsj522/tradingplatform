package com.qishengjie.tradingplatform.controller;


import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.service.ProductCategoryService;
import com.qishengjie.tradingplatform.vo.LevelOneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 商品分类表 前端控制器
 * </p>
 *
 * @author Shengjie Qi
 * @since 2021-04-10
 */
@Controller
@RequestMapping("/productCategory")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;
//    @Autowired
//    private CartService cartService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession session) {
        return productCategoryService.list(session);
    }

    @GetMapping("/getLevelOne")
    @ResponseBody
    public Object getLevelOne() {
        return productCategoryService.getLevelOne();
    }

    @GetMapping("/getLevelTwo")
    @ResponseBody
    public Object getLevelTwo(String parentId) {
        return productCategoryService.getLevelTwo(parentId);
    }
}

