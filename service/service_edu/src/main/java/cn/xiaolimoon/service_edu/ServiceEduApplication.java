package cn.xiaolimoon.service_edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "cn.xiaolimoon")
public class ServiceEduApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceEduApplication.class, args);
    }

}
