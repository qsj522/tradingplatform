package com.qishengjie.tradingplatform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.mapper.UserMapper;
import com.qishengjie.tradingplatform.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.qishengjie.tradingplatform.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String register(User user, Model model) {
        boolean result = false;
        try {
            //设定初始信誉分
            user.setUserId(UUIDUtils.getUUID());
            user.setCreditScore(100);
            result = SqlHelper.retBool(this.baseMapper.insert(user));
        } catch (Exception e) {
            model.addAttribute("error", "用户名"+user.getLoginName() + "已存在！请重新输入！");
            return "register";
        }
        if (result) return "login";
        return "register";
    }

    @Override
    public String login(String loginName, String password, HttpSession session) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        wrapper.eq("password", password);
        User user = this.baseMapper.selectOne(wrapper);
        if (user == null) {
            return "redirect:/login";
        } else {
            session.setAttribute("user", user);
            return "redirect:/productCategory/list";
        }
    }

    @Override
    public ModelAndView userInfo(HttpSession session) {
        User user = (User) session.getAttribute("user");
        String id = user.getUserId();
        User newOne = this.userMapper.selectById(id);
        session.setAttribute("user",newOne);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        return modelAndView;
    }

    @Override
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @Override
    public ModelAndView updateUserInfo(User user,HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            this.userMapper.updateUserInfo(user);
        } catch (Exception e) {
            modelAndView.setViewName("update");
            modelAndView.addObject("error", "请检查用户信息是否符合规范！");
            return modelAndView;
        }
        User newOne = this.userMapper.selectById(user.getUserId());
        session.setAttribute("user",newOne);
        modelAndView.addObject("session",session);
        modelAndView.setViewName("userInfo");
      return modelAndView;
    }
}
