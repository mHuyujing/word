package org.hyj.controller;

import org.hyj.bean.User;
import org.hyj.dao.UserMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
@Controller
public class IndexController {
    @RequestMapping(value = ("/index.do"), method = RequestMethod.GET)
    public String index(ModelMap mm, HttpServletRequest req, String wordName) {
//        WebApplicationContext context = WebApplicationContextUtils
//                .getWebApplicationContext(req.getServletContext());
//        mm.addAttribute("wordName", wordName);
        return "index";
    }
     @RequestMapping(value = ("/login.do"), method = RequestMethod.GET)
    public String login(ModelMap modelMap, HttpServletRequest req, User user) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        User user1 = userMapper.selectUserByName(user.getUserName());
        if (user1 == null) {
            modelMap.addAttribute("info", "用戶不存在");
            return "error";
        } else if (user1.getUserPassword().equals(user.getUserPassword())) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user1);
            return "index";
        } else {
            modelMap.addAttribute("info", "密码错误");
            return "error";
        }
    }

    @ResponseBody
    @RequestMapping(value = ("/reg.do"), method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String reg(HttpServletRequest req, User user) {
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(req.getServletContext());
        UserMapper userMapper = (UserMapper) context.getBean("userMapper");
        try {
            userMapper.insert(user);
            return "1";
        } catch (Exception e) {
            return "0";
        }
    }

}
