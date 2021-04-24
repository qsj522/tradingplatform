package com.qishengjie.tradingplatform.service;

import com.qishengjie.tradingplatform.vo.ResultVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Shengjie Qi
 */
public interface ImageService {
    ResultVO saveImg(MultipartFile file) ;
}
