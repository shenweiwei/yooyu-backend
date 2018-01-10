package com.yooyu.backend.webapp.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;
import org.springframework.context.annotation.Configuration;

@Configuration
@ApplicationPath("/rest")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		scanPackages("com.yooyu.backend.webapp.controllers" , "com.yooyu.backend.webapp.filters");
	}

	private void scanPackages(String... packages) {
		for (String pack : packages) {
			Reflections reflections = new Reflections(pack);
			registerClasses(reflections.getTypesAnnotatedWith(Path.class));
			registerClasses(reflections.getTypesAnnotatedWith(Provider.class));
			register(MultiPartFeature.class); 
		}
		
		
	}
}
