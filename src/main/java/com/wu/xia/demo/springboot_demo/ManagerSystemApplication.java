package com.wu.xia.demo.springboot_demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ManagerSystemApplication extends SpringBootServletInitializer {

	// 注意下面App.class 配置自己的启动类
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(new Class[] {ManagerSystemApplication.class});
	}




}
