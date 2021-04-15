package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.entity.UserAddress;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author Shengjie Qi 
 * @since 2021-04-10
 */
public interface UserAddressService extends IService<UserAddress> {
    ModelAndView list(HttpSession session);
}
