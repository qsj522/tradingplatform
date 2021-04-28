package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


/**
 * @author Shengjie Qi
 */
public interface ImageService {
    ResultVO saveImg(MultipartFile file) ;

    ResultVO userImg(MultipartFile file, HttpSession session) ;
}
