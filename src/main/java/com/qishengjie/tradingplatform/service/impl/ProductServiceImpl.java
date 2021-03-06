package com.qishengjie.tradingplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qishengjie.tradingplatform.entity.Product;
import com.qishengjie.tradingplatform.entity.ProductCategory;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.mapper.ProductCategoryMapper;
import com.qishengjie.tradingplatform.mapper.ProductMapper;
import com.qishengjie.tradingplatform.mapper.UserMapper;
import com.qishengjie.tradingplatform.service.ProductCategoryService;
import com.qishengjie.tradingplatform.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishengjie.tradingplatform.util.UUIDUtils;
import com.qishengjie.tradingplatform.vo.ResultVO;
import com.qishengjie.tradingplatform.vo.TableDataVO;
import com.qishengjie.tradingplatform.vo.TableProductVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * 商品信息表 服务实现类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductCategoryService productCategoryService;
    @Autowired
    private UserMapper userMapper;

    @Override
    public ModelAndView list(String type, String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList",findByCategoryId(type,id));
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
//            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @Override
    public ModelAndView findByKey(String keyWord, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        //根据关键字查询
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.like("name",keyWord);
        modelAndView.addObject("productList",this.baseMapper.selectList(wrapper));
        modelAndView.addObject("list",productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if(user == null){
            modelAndView.addObject("cartList",new ArrayList<>());
        }else{
//            modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @Override
    public ModelAndView findById(String id, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        Product product = productMapper.selectByProductId(id);
        modelAndView.addObject("product",product);
        modelAndView.addObject("productUser",userMapper.selectById(product.getUserId()));
        return modelAndView;
    }

    @Override
    public List<Product> findByCategoryId(String type, String categoryId) {
        Map<String,Object> map = new HashMap<>();
        map.put("Category_level_"+type+"_id",categoryId);
        return productMapper.selectByMap(map);
    }

    @Override
    public ResultVO saveBatch(Product product,HttpSession session) {
        //添加字段
        String id = UUIDUtils.getUUID();
        product.setProductId(id);
        product.setCategoryLevelThreeId(id);
        User user = (User)session.getAttribute("user");
        product.setUserId(user.getUserId());
        product.setIsTrading(0);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = df.format(new Date());
        product.setCreatedTime(date);
        product.setCreator(user.getNickname());
        Boolean productStatus = save(product);
        Boolean categoryStatus = false;
        //分类表添加
        if (!StringUtils.isEmpty(product.getCategoryLevelTwoId())){
            ProductCategory entity = new ProductCategory();
            entity.setId(id);
            entity.setName(product.getName());
            entity.setParentId(product.getCategoryLevelTwoId());
            entity.setType(3);
            categoryStatus = SqlHelper.retBool(productCategoryMapper.insert(entity));
        }else {
            log.error("未选择分类或者分类不存在！");
        }
        ResultVO result = new ResultVO();
//        String result = "";
        if (productStatus && categoryStatus){
            result.setStatus(0);
            result.setDesc("添加成功！");
            result.setUrl("/productCategory/list");
//            result = "redirect:/productCategory/list";
        }else {
            result.setStatus(1);
            result.setDesc("添加失败，请检查商品内容是否合法");
            result.setUrl("releaseProduct");
//            result = "releaseProduct";
        }
        return result;
    }

    @Override
    public TableDataVO<TableProductVO> findAllTableData(Integer page, Integer limit) {
        IPage<Product> productIPage = new Page<>(page,limit);
        IPage<Product> result = productMapper.selectPage(productIPage,null);
        List<Product> productList = result.getRecords();
        List<TableProductVO> tableProductVOList = new ArrayList<>();
        QueryWrapper wrapper;
        ProductCategory productCategory;
        for (Product product : productList) {
            TableProductVO tableProductVO = new TableProductVO();
            BeanUtils.copyProperties(product,tableProductVO);
            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryLevelOneId());
            productCategory = productCategoryMapper.selectOne(wrapper);
            if(productCategory != null){
                tableProductVO.setLevelOne(productCategory.getName());
            }


            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryLevelTwoId());
            productCategory = productCategoryMapper.selectOne(wrapper);
            if(productCategory != null){
                tableProductVO.setLevelTwo(productCategory.getName());
            }


            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryLevelThreeId());
            productCategory = productCategoryMapper.selectOne(wrapper);
            if(productCategory != null){
                tableProductVO.setLevelThree(productCategory.getName());
            }
            tableProductVOList.add(tableProductVO);
        }
        TableDataVO tableDataVO = new TableDataVO();
        tableDataVO.setCode(0);
        tableDataVO.setMsg("");
        tableDataVO.setData(tableProductVOList);
        tableDataVO.setCount(result.getTotal());
        return tableDataVO;
    }
}
