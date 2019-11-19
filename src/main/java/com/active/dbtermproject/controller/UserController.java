package com.active.dbtermproject.controller;

import com.active.dbtermproject.domain.User;
import com.active.dbtermproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController { // front와 backend 연결 다리 역할

    @Autowired
    private UserService userService;

    @GetMapping
    public String check() {
        return "Welcome";
    }

    @GetMapping(path = "/getusernames")
    public List<String> getUserNames() {
        return this.userService.getAllUserNames();
    }

    @PostMapping(path = "/insert")
    public int insertUser(@RequestBody User user) {
        return this.userService.insertService(user);
    }

    @GetMapping("/test") // localhost/user/test
    public String test(Model model) {
        model.addAttribute("user", new User());

        return "test";       // 실제 호출될페이지 : /WEB-INF/jsp/test.jsp
    }
}
