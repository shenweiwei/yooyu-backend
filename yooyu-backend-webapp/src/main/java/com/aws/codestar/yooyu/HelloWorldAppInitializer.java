package com.aws.codestar.yooyu;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.aws.codestar.yooyu.configuration.MvcConfig;

/**
 * Utility to initialize the Spring MVC HelloWorld application.
 */
public class HelloWorldAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                MvcConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/"
        };
    }

}
