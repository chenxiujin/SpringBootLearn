package com.test.springbm.service;

import com.test.springbm.entity.User;
import com.test.springbm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    /**
     * 增加一条记录
     * @param user
     * @return 增加记录的条数
     */
    public int insert(User user){
        return this.userMapper.insert(user);
    }

    /**
     * 根据id删除一条记录
     * @param id
     * @return 删除成功：true；删除失败：false
     */
    public boolean delete(Integer id){
        return this.userMapper.deleteByPrimaryKey(id) > 0;
    }

    /**
     * 修改记录
     * @param user
     * @return 修改记录的条数
     */
    public int update(User user){
        return this.userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 根据id查询
     * @param id
     * @return user
     */
    public User selectUserById(Integer id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectAll(){
        return this.userMapper.selectAll();
    }

}
