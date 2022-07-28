package com.vince.xq.dataCompare.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.vince.xq.dataCompare.common.Constant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author xiaoqiu2017wy@163.com
 * @Date 2022/7/9
 */
@RequestMapping("/login")
@Controller
public class LoginController {
    private static final Logger log= LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String userId, String password) {
        if (userId.equals("admin")&&password.equals("admin")){
            return "redirect:/index.html";
        }else {
            return "login";
        }
    }

}
