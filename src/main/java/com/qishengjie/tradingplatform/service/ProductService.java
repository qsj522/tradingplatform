package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qishengjie.tradingplatform.vo.ResultVO;
import com.qishengjie.tradingplatform.vo.TableDataVO;
import com.qishengjie.tradingplatform.vo.TableProductVO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 * 商品信息表 服务类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface ProductService extends IService<Product> {

    ModelAndView list(String type,Integer id, HttpSession session);

    ModelAndView findByKey(String keyWord,HttpSession session);

    ModelAndView findById(String id,HttpSession session);

     List<Product> findByCategoryId(String type, Integer categoryId);

    ResultVO saveBatch(Product product, HttpSession session) ;

    /**
     * 后台管理系统返回商品数据
     */
     TableDataVO<TableProductVO> findAllTableData(Integer page, Integer limit);
}
