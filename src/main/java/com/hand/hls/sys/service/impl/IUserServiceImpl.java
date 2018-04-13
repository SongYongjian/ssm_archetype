package com.hand.hls.sys.service.impl;


import com.hand.hls.sys.dto.User;
import com.hand.hls.sys.mapper.UserMapper;
import com.hand.hls.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
