package com.hand.hls;


import com.hand.hls.sys.dto.User;
import com.hand.hls.sys.service.IUserService;
import com.hand.hls.util.component.RedisCacheUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;


/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/applicationContext.xml"})
public class IUserServiceTest {

    @Autowired
    public IUserService userService;
    @Autowired
    private RedisCacheUtil<User> redisCache;

    @Test
    public void getUserByIdTest() {

        User user = userService.getUserById(1);
        System.out.println(user.getUserName());
        //调用Redis
        Map<Integer, User> userMap = redisCache.getCacheIntegerMap("userMap");
        for (int key : userMap.keySet()) {
            System.out.println("key = " + key + ",value=" + userMap.get(key).getUserName());
        }


    }

}
