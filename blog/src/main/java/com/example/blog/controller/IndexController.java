package com.example.blog.controller;

import com.example.blog.mapper.UserMapper;
import com.example.blog.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/index")
    public String index(HttpServletRequest request) {
//      验证数据库中是否由当前的token，若有则登录成功
        Cookie[] cookies = request.getCookies();
        String token="";
        if(cookies==null){
            return "index";
        }
        else{
            for(Cookie cookie:cookies){
                if(cookie!=null&&cookie.getName().equals("token")){
                    token= cookie.getValue();
                    User user=userMapper.findToken(token);
                    if(user!=null){
                        request.getSession().setAttribute("user",user);
                    }
                    break;
                }
            }
        }


        return "index";
    }
}