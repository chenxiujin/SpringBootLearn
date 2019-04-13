package com.test.springbm.service;

import com.test.springbm.entity.User;
import com.test.springbm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CXP
 * @date 2019/4/11 18:37
 * @version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public User selectUserById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

}
