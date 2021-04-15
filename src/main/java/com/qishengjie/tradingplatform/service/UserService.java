package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface UserService extends IService<User> {
    //注册
    String register(User user, Model model);

    //登录
    String login(String loginName, String password, HttpSession session);

    //用户信息
    ModelAndView userInfo(HttpSession session);

    //退出
    String logout(HttpSession session);
}
