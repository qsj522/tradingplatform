package com.qishengjie.tradingplatform.controller;


import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author Shengjie Qi
 * @since 2021-04-10
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CartService cartService;

    @PostMapping("/register")
    public String register(User user, Model model) {
        return userService.register(user, model);
    }

    /**
     * 登录
     *
     * @param loginName
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session) {
        return userService.login(loginName, password, session);
    }

    /**
     * 退出
     *
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        return userService.logout(session);
    }

    /**
     * 用户信息
     */
    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session) {
        return userService.userInfo(session);
    }

    @PostMapping("/update")
    public ModelAndView update(User user,HttpSession session) {
        return userService.updateUserInfo(user,session);
    }
}

