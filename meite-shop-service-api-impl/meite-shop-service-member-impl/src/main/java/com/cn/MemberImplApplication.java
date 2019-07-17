package com.cn;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableApolloConfig
@EnableSwagger2
@MapperScan(basePackages = "com.cn.mapper")
public class MemberImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemberImplApplication.class, args);
	}

}
