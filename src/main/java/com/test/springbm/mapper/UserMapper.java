package com.test.springbm.mapper;

import com.test.springbm.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author CXP
 * @date
 * @version 1.0
 */
@Mapper
@Repository
public interface UserMapper {
    /**
     * 通过id删除记录
     * @param id
     * @return 删除记录的条数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加一条记录
     * @param record
     * @return 增加记录的条数
     */
    int insert(User record);

    /**
     * 对增加的数据去空格
     * @param record
     * @return 增加记录的条数
     */
    int insertSelective(User record);

    /**
     * 根据id查询记录
     * @param id
     * @return user
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 修改记录
     * @param record
     * @return 修改记录的条数
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 修改记录
     * @param record
     * @return 修改记录的条数
     */
    int updateByPrimaryKey(User record);
}