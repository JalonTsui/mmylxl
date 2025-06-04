package org.jalontsui.sb.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * @author JalonTsui
 * @Date 22:37 2025/6/4    
 **/

@SpringBootApplication
@MapperScan(basePackages = {"org.jalontsui.sb.mysql.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
