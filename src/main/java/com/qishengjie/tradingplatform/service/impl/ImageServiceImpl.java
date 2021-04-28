package com.qishengjie.tradingplatform.service.impl;

import com.qishengjie.tradingplatform.entity.User;
import com.qishengjie.tradingplatform.service.ImageService;
import com.qishengjie.tradingplatform.service.UserService;
import com.qishengjie.tradingplatform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;

/**
 * @author Shengjie Qi
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Autowired
    private UserService userService;

    @Override
    public ResultVO saveImg(MultipartFile file) {
        String path = "";
        String trueFileName = "";
        ResultVO resultVO = new ResultVO();
        if (file != null) {
            String fileName = file.getOriginalFilename();// 文件原名称
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    try {
                        path = ResourceUtils.getURL("classpath:").getPath() + "static/images/";
                        // 自定义的文件名称
                        trueFileName = System.currentTimeMillis() + "." + type;
                        path += trueFileName;
                        file.transferTo(new File(path));
                    }catch (Exception e){
                        log.error(e.toString());
                        resultVO.setStatus(1);
                        resultVO.setDesc("图片上传失败" + e);
                        resultVO.setUrl(null);
                        return resultVO;
                    }
                }
                else {
                    resultVO.setStatus(1);
                    resultVO.setDesc("位置图片类型，请选择PNG、JPG或GIF类型图片");
                    resultVO.setUrl(null);
                    return resultVO;
                }
            }
        } else {
            resultVO.setStatus(1);
            resultVO.setDesc("未选择图片！");
            resultVO.setUrl(null);
            return resultVO;
        }
        resultVO.setStatus(0);
        resultVO.setDesc("图片上传成功");
        resultVO.setUrl(trueFileName);
        return resultVO;
    }

    @Override
    public ResultVO userImg(MultipartFile file , HttpSession session) {
        String path = "";
        String trueFileName = "";
        ResultVO resultVO = new ResultVO();
        if (file != null) {
            String fileName = file.getOriginalFilename();// 文件原名称
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    try {
                        path = ResourceUtils.getURL("classpath:").getPath() + "static/images/";
                        // 自定义的文件名称
                        trueFileName = System.currentTimeMillis() + "." + type;
                        path += trueFileName;
                        file.transferTo(new File(path));
                        //修改用户的头像信息
                        User oldOne  = (User) session.getAttribute("user");
                        User newOne = new User();
                        newOne.setUserId(oldOne.getUserId());
                        newOne.setFileName(trueFileName);
                        userService.updateUserInfo(newOne,session);
                    }catch (Exception e){
                        log.error(e.toString());
                        resultVO.setStatus(1);
                        resultVO.setDesc("图片上传失败" + e);
                        resultVO.setUrl(null);
                        return resultVO;
                    }
                }
                else {
                    resultVO.setStatus(1);
                    resultVO.setDesc("位置图片类型，请选择PNG、JPG或GIF类型图片");
                    resultVO.setUrl(null);
                    return resultVO;
                }
            }
        } else {
            resultVO.setStatus(1);
            resultVO.setDesc("未选择图片！");
            resultVO.setUrl(null);
            return resultVO;
        }
        resultVO.setStatus(0);
        resultVO.setDesc("图片上传成功");
        resultVO.setUrl(trueFileName);
        return resultVO;
    }
}
