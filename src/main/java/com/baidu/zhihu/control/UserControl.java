package com.baidu.zhihu.control;

import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.baidu.zhihu.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.baidu.zhihu.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping(path = "/zhihu")
public class UserControl {

    @Autowired
    private UserService userService;

    private final Logger LOG = LoggerFactory.getLogger(UserControl.class);

    @PostMapping("/register")
    public void loginRegister(@RequestParam String name, @RequestParam String password, HttpServletResponse response)
            throws IOException {

        List<User> users = userService.list(name);

        if (users == null || users.size() == 0) {
            User user = new User();
            user.setName(name);
            user.setPassword(password);
            userService.addUser(user);
            LOG.info("注册成功");
        }

        else {
            users.forEach(user -> {
                if (user.getPassword().equals(password)) {
                    LOG.info("登录成功");
                }
            });
        }
        //获取user的id
        String userId = userService.get(name, password).getId();
        response.sendRedirect("/zhihu/homePage_logsuccess?userId="+userId);
    }
}
