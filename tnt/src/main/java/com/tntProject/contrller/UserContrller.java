package com.tntProject.contrller;

import com.tntProject.model.UserModel;
import com.tntProject.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * Created by 18487 on 2019/7/23.
 */

@Controller
@RequestMapping(value = "/user")
public class UserContrller {
    @Autowired
    UserServiceImp userServiceImp;

    @PostMapping("login_validate")
//    @ResponseBody
    public ModelAndView loginValidate(@RequestParam("username")String username,
                                      @RequestParam("password")String password,
                                      HttpSession session){
//        String msg = "welcome";
//        System.out.println("欢迎登陆");
        ModelAndView modelAndView = userServiceImp.login_validate(username, password);

        session.setAttribute("user",username);
        return modelAndView;
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session){
        session.removeAttribute("user");
        return new ModelAndView("index"); //传参数的方式返回视图
    }

    //处理注册请求
    @PostMapping("/register_validate")
    //ModelAttribute 实体属性注入 input.name = model.attr
    public ModelAndView registerValidate(@ModelAttribute UserModel userModel,@RequestParam("password2")String password2){
        System.out.println("registerValidate"+userModel.getUsername()+userModel.getPassword()+password2);
        ModelAndView modelAndView = userServiceImp.register_validate(userModel,password2);
        return modelAndView;
    }
}

