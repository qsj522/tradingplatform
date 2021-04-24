package com.qishengjie.tradingplatform.service.impl;

import com.qishengjie.tradingplatform.service.ImageService;
import com.qishengjie.tradingplatform.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * @author Shengjie Qi
 */
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    @Override
    public ResultVO saveImg(MultipartFile file) {
        String path = "";
        ResultVO resultVO = new ResultVO();
        if (file != null) {
            String fileName = file.getOriginalFilename();// 文件原名称
            String type = fileName.contains(".") ? fileName.substring(fileName.lastIndexOf(".") + 1) : null;
            if (type != null) {// 判断文件类型是否为空
                if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
                    try {
                        path = ResourceUtils.getURL("classpath:").getPath() + "static/images/";
                        // 自定义的文件名称
                        String trueFileName = System.currentTimeMillis() + "." + type;
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
        resultVO.setUrl(path);
        return resultVO;
    }
}
