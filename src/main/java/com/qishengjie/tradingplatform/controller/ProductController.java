package com.qishengjie.tradingplatform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qishengjie.tradingplatform.entity.Product;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.service.ProductCategoryService;
import com.qishengjie.tradingplatform.service.ProductService;
import com.qishengjie.tradingplatform.vo.ResultVO;
import com.qishengjie.tradingplatform.vo.TableDataVO;
import com.qishengjie.tradingplatform.vo.TableProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * <p>
 * 商品信息表 前端控制器
 * </p>
 *
 * @author Shengjie Qi
 * @since 2021-04-10
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
//    @Autowired
//    private CartService cartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(
            @PathVariable("type") String type,
            @PathVariable("id") String id,
            HttpSession session
    ) {
       return productService.list(type, id, session);
    }

    @PostMapping("/findByKey")
    public ModelAndView findByKey(String keyWord, HttpSession session) {
        return productService.findByKey(keyWord,session);
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(@PathVariable("id") String id, HttpSession session) {
        return productService.findById(id, session);
    }

    @RequestMapping("/findAllTableProduct")
    @ResponseBody
    public TableDataVO<TableProductVO> findAllTableProduct(Integer page, Integer limit) {
        return productService.findAllTableData(page, limit);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultVO save(Product product, HttpSession session) {
         return productService.saveBatch(product,session);
    }

}

