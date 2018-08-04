package com.tamguo.config.web;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

@Component
public class ThymeleafConfig implements EnvironmentAware{

	@Resource
    private Environment env;

	@Resource
	private void configureThymeleafStaticVars(ThymeleafViewResolver viewResolver) {
	    if(viewResolver != null) {
	        Map<String, Object> vars = new HashMap<>();
	        vars.put("domainName", env.getProperty("domain.name"));
	        viewResolver.setStaticVariables(vars);
	    }
	}

	@Override
	public void setEnvironment(Environment environment) {
		env = environment;
	}

}
