package com.qindong.service;

import com.qindong.dao.UserMapper;
import com.qindong.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author qind6
 * @date 2019/6/24
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public int insertUser(User user){
        return userMapper.insertSelective(user);
    }

    public int updateUser(User user){
        return userMapper.updateByPrimaryKeySelective(user);
    }

    public User selectByPrimaryKey(int id){
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> selectUsers(User user){
        UserExample userExample=new UserExample();
        PackExample.packingExample(user,userExample.createCriteria());
        return userMapper.selectByExample(userExample);
    }

    public int delUser(int id){
        return userMapper.deleteByPrimaryKey(id);
    }
}
