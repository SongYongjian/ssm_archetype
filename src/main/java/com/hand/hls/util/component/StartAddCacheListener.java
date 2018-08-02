package com.hand.hls.util.component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.hand.hls.sys.dto.User;
import com.hand.hls.sys.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

/**
 * 监听器，用于项目启动的时候初始化信息(redis)
 */

@Service
public class StartAddCacheListener implements ApplicationListener<ContextRefreshedEvent> {

    //日志
    private final Logger log = Logger.getLogger(StartAddCacheListener.class);

    @Autowired
    private RedisCacheUtil<Object> redisCache;

    @Autowired
    private IUserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //读取配置文件
        ResourceBundle resource = ResourceBundle.getBundle("spring/config");
        String enabled = resource.getString("quartz.enabled");

        if ("true".equals(enabled)) {
            //spring 启动的时候缓存
            if (event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
                System.out.println("\n\n\n_________\n\n缓存数据 \n\n ________\n\n\n\n");
                List<User> userList = userService.selectAll();
                Map<Integer, User> userMap = new HashMap<Integer, User>();
                for (int i = 0; i < userList.size(); i++) {
                    userMap.put(userList.get(i).getId(), userList.get(i));
                }
                redisCache.setCacheIntegerMap("userMap", userMap);
            }
        }
    }
}
