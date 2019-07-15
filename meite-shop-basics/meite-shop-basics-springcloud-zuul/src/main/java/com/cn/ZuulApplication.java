package com.cn;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableApolloConfig
@EnableSwagger2
public class ZuulApplication {

    //获取Apollo config
    @ApolloConfig
    private Config config;

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	// 添加文档来源
	@Component
	@Primary
	class DocumentationConfig implements SwaggerResourcesProvider {
		@Override
		public List<SwaggerResource> get() {
			/*List resources = new ArrayList<>();
			resources.add(swaggerResource("app-member", "/app-member/v2/api-docs", "2.0"));
			resources.add(swaggerResource("app-wechat", "/app-wechat/v2/api-docs", "2.0"));*/
			return getResources();
		}

		/**
		 * 方法描述 从阿波罗服务器获取resource
		 * @param
		 * @author luoxd
		 * @date 2019年07月15日 16:22:18
		 * @return
		 */
		public List<SwaggerResource> getResources(){
		    List list = new ArrayList();
		    String swaggerConfig = getSwaggerConfig();
            JSONArray jsonArray = JSONArray.parseArray(swaggerConfig);
            for (Object object: jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                String name = jsonObject.getString("name");
                String location = jsonObject.getString("location");
                String version = jsonObject.getString("version");
                list.add(swaggerResource(name, location, version));
            }
            return list;
        }

		/**
		 * 方法描述 获取swagger config 配置
		 * @param
		 * @author luoxd
		 * @date 2019年07月15日 16:19:25
		 * @return
		 */
		public String getSwaggerConfig(){
		    String property = config.getProperty("zuul.swagger.config","");
		    return  property;
        }

		private SwaggerResource swaggerResource(String name, String location, String version) {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(name);
			swaggerResource.setLocation(location);
			swaggerResource.setSwaggerVersion(version);
			return swaggerResource;
		}

	}

}
