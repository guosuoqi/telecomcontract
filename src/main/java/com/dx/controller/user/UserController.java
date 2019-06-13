package com.dx.controller.user;


import com.dx.model.user.UserMain;
import com.dx.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("toIndex")
    public String toIndex(Model model, HttpServletRequest request) {
        HttpSession session =request.getSession();
        model.addAttribute("user",session.getAttribute(session.getId()));
        return "view/menu";
    }

    /**
     * 登录
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, String> login(UserMain user, HttpServletRequest request, Model model) {
        HashMap<String, String> result = new HashMap<String, String>();
        HttpSession session =request.getSession();
        //根据账号查询用户信息
        UserMain userInfo =userService.getUserInfoByLoginNumber(user.getLoginNumber());
        if(userInfo==null){
            result.put("code","1");
            result.put("msg", "用户名错误");
            logger.info(this.getClass() + "，用户名错误！");
            return result;
        }

        if (!userInfo.getPassword().equals(user.getPassword())){
            result.put("code", "2");
            result.put("msg", "用户名或密码错误");
            logger.info(this.getClass() + "，用户名或密码错误！");
            return result;
        }
        session.setAttribute(session.getId(), userInfo);
        result.put("code", "200");
        result.put("msg", "登录成功");
        return result;
    }

    //注销
    @RequestMapping(value = "exitLogin")
    public String exitLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute(session.getId())!=null){
            session.removeAttribute(session.getId());
        }
        return "login";
    }


}