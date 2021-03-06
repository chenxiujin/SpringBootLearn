package com.test.springbm.controller;

import com.test.springbm.entity.User;
import com.test.springbm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insert(User user){
        return this.userService.insert(user);
    }

    @RequestMapping(value = "/delete")
    public boolean delete(Integer id){
        return this.userService.delete(id);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public int update(User user){
        return this.userService.update(user);
    }

    @RequestMapping(value = "/select")
    public User selectByUserId(Integer id){
        return this.userService.selectUserById(id);
    }

    @RequestMapping(value = "/selects")
    public List<User> selectALl(){
        return this.userService.selectAll();
    }
}
