package com.hand.hls.sys.service;

import com.hand.hls.sys.dto.User;

import java.util.List;

public interface IUserService {

    User getUserById(int id);
    List<User> selectAll();
    User insertSelective(User user);
    User getUserByName(String userName);
}

