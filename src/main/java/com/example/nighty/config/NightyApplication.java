package com.example.nighty.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.example")
@SpringBootApplication
@MapperScan("com.example.nighty.mapper")
@EnableScheduling
@EnableAsync
public class NightyApplication {

    private static final Logger LOG = LoggerFactory.getLogger(NightyApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(NightyApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("Starting Success！！");
        LOG.info("url: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }

}
