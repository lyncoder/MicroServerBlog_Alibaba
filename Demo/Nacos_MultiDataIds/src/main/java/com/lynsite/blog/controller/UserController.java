package com.lynsite.blog.controller;

import com.lynsite.blog.model.User;
import com.lynsite.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/4/2 10:56
 * @Version: 1.0
 */

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") Long id) {
        return userService.findById(id);
    }

}
