package com.qishengjie.tradingplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.entity.UserAddress;
import com.qishengjie.tradingplatform.mapper.UserAddressMapper;
import com.qishengjie.tradingplatform.service.UserAddressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddress> implements UserAddressService {

    @Override
    public ModelAndView list(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        User user = (User)session.getAttribute("user");
//        modelAndView.addObject("cartList",cartService.findAllCartVOByUserId(user.getId()));
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getUserId());
        modelAndView.addObject("addressList",this.baseMapper.selectList(wrapper));
        return modelAndView;

    }
}
