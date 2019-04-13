package com.test.springbm.controller;

import com.test.springbm.entity.User;
import com.test.springbm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CXP
 * @version 1.0
 * @date 2019/4/12 22:34
 */
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET,RequestMethod.POST})
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "/select")
    public User selectByUserId(Integer id){
        return this.userService.selectUserById(id);
    }
}
