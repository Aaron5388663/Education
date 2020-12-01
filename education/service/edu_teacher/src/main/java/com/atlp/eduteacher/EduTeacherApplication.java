package com.atlp.eduteacher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.atlp"}) // 标识跨包扫描注解
@EnableSwagger2
public class EduTeacherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduTeacherApplication.class, args);
    }
}
