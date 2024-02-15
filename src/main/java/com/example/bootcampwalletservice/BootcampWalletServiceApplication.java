package com.example.bootcampwalletservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaRepositories
@EnableMongoRepositories
@EnableTransactionManagement
@EnableCaching
@EnableFeignClients
@EntityScan
@EnableAspectJAutoProxy
@EnableWebMvc
public class BootcampWalletServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootcampWalletServiceApplication.class, args);
    }

}
