package com.qishengjie.tradingplatform.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package: com.qishengjie.tradingplatform.controller
 * @ClassName: RedirectController
 * @Author: SamSung
 * @CreateTime: 2021-04-10 13:05
 * @Description:
 */
@Controller
public class RedirectController {

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @GetMapping("/")
    public String main(){
        return "redirect:/productCategory/list";
    }
}
