package com.qishengjie.tradingplatform.controller;

import com.qishengjie.tradingplatform.service.ImageService;
import com.qishengjie.tradingplatform.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @author Shengjie Qi
 */
@Controller
@RequestMapping("/image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/save")
    @ResponseBody
    public ResultVO saveImg(MultipartFile file) {
            return imageService.saveImg(file);
    }

    @PostMapping("/userImg")
    @ResponseBody
    public ResultVO userImg(MultipartFile file, HttpSession session) {
        return imageService.userImg(file,session);
    }
}
