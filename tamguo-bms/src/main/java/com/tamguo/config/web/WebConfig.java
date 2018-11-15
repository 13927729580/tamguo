package com.tamguo.config.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tamguo.interceptor.MemberInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Value("${file.storage.path}")
	private String fileStoragePath;
	@Autowired
	private MemberInterceptor memberInterceptor;
	
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	   registry.addResourceHandler("/files/**").addResourceLocations("file:"+fileStoragePath);
    }
    
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	//	registry.addInterceptor(memberInterceptor).addPathPatterns("/member/**");
	}
}
