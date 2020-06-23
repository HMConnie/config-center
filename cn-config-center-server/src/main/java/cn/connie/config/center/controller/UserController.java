package cn.connie.config.center.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

//    @RequestMapping("/login.html")
//    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password) {
//        if(StringUtils.isNotBlank(username)&&StringUtils.isNotBlank(password)){
//            if(username.trim().equals("sgcaiCCadmin")&& DigestUtils.md5Hex(password.trim()).equals("e65e4a0eb375ee4937853f75d0e0f661")){
//                return new ModelAndView("index");
//            }
//        }
//        return new ModelAndView("login");
////        return new ModelAndView("index");
//    }

    @RequestMapping("/login.html")
    public ModelAndView login() {
        return new ModelAndView("index");
    }
}
