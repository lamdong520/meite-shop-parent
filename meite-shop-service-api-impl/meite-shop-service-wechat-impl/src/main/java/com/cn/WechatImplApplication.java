package com.cn;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableEurekaClient
@EnableApolloConfig
@EnableSwagger2
@EnableFeignClients
public class WechatImplApplication {

	public static void main(String[] args) {
		SpringApplication.run(WechatImplApplication.class, args);
	}

}
