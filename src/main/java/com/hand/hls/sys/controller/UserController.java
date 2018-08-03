package com.hand.hls.sys.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hand.hls.sys.dto.User;
import com.hand.hls.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/showname", method = RequestMethod.GET)
    public String showUserName(@RequestParam("uid") int uid, HttpServletRequest request, Model model) {
        User user = userService.getUserById(uid);
        if (user != null) {
            request.setAttribute("name", user.getUserName());
            model.addAttribute("name", user.getUserName());
            model.addAttribute("test", "TEST");
            return "showName";
        }
        request.setAttribute("error", "没有找到该用户！");
        return "error";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session) {
        //获取用户名和密码
        String username = user.getUserName();
        String password = user.getPassword();
        //些处横板从数据库中获取对用户名和密码后进行判断
        if (username != null && username.equals("admin") && password != null && password.equals("admin")) {
            //将用户对象添加到Session中
            session.setAttribute("USER_SESSION", user);
            //重定向到主页面的跳转方法
            return "redirect:main";
        }
        model.addAttribute("msg", "用户名或密码错误，请重新登录！");
        return "login";
    }

    @RequestMapping(value = "/main")
    public String toMain() {
        return "main";
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        //清除session
        session.invalidate();
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }

}
