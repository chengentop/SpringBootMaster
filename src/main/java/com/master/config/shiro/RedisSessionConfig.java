package com.master.config.shiro;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//@Configuration
////在这里设置Session过期时间，单位：秒
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 36000)
public class RedisSessionConfig {
//    @Bean
//    public static ConfigureRedisAction configureRedisAction() {
//        return ConfigureRedisAction.NO_OP;
//    }
}
