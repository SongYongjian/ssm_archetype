package com.hand.hls.util.component;

import com.hand.hls.sys.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 静态job applicationContext.xml 配置
 */

public class ExpireJobTask {
    @Autowired
    IUserService userService;
    private final Logger log = Logger.getLogger(ExpireJobTask.class);

    public void doBiz() {

        // 执行业务逻辑
        System.out.println("定时任务开启" +  (System.currentTimeMillis()));

    }

}
