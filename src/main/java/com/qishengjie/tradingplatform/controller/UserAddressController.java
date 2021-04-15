package com.qishengjie.tradingplatform.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户地址表 前端控制器
 * </p>
 *
 * @author Shengjie Qi
 * @since 2021-04-10
 */
@Controller
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;
//    @Autowired
//    private CartService cartService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession session) {
        return userAddressService.list(session);
    }
}

