package com.hand.hls.sys.mapper;


import com.hand.hls.sys.dto.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    User insert(User record);

    User insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}