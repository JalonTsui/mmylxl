package org.jalontsui.login;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.jalontsui.login.dto.RegisterRequestBody;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"org.jalontsui.login"})
@RestController
@Slf4j
public class LoginApp {
    public static void main(String[] args) {
        SpringApplication.run(LoginApp.class, args);
    }

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 注意requestBody注解一个请求只能绑定一个
     * @param body
     * @return
     */
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestBody body) {
        redisTemplate.opsForValue().set(body.getKey(), body.getValue());
        log.error("设置redisKey:{},value:{}", body.getKey(), body.getValue());
        return "test";
    }

    @PostMapping("/login")
    public String login() {
        // todo 登录时，把token存入redis中
        return "test";
    }

    @PostMapping("/doSthAfterLogin")
    public String doSthAfterLogin(){
        // todo 登录校验,aop实现
        return null;
    }

    @PostMapping("/logout")
    public String logout(){
        // todo 移除对应的token
        return null;
    }
}
