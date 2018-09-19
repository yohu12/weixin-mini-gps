package com.github.binarywang.demo.wechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@SpringBootApplication()
public class WxMaDemoApplication extends SpringBootServletInitializer {


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(WxMaDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WxMaDemoApplication.class, args);
    }
}
