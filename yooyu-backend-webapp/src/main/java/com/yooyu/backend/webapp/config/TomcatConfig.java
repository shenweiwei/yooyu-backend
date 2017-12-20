package com.yooyu.backend.webapp.config;

import java.nio.charset.Charset;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11Nio2Protocol;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * tomcat 配置
 * @author Shenweiwei
 */
@Configuration
public class TomcatConfig {
	//uri编码
	private final static String uriEncoding = "UTF-8";
	//采用NIO2
	private final static String aprProtocol = "org.apache.coyote.http11.Http11Nio2Protocol";

    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
        tomcat.setProtocol(aprProtocol);
        tomcat.setUriEncoding(Charset.forName(uriEncoding));
        tomcat.addConnectorCustomizers(new AdvanceTomcatConnectorCustomizer());
        return tomcat;
    }

    public class AdvanceTomcatConnectorCustomizer implements TomcatConnectorCustomizer {  
        public void customize(Connector connector)  
        {  
            Http11Nio2Protocol protocol = (Http11Nio2Protocol) connector.getProtocolHandler();  
            //设置最大连接数  
            protocol.setMaxConnections(2000);     
            //设置最大线程数  
            protocol.setMaxThreads(2000);  
            //初始化线程数
            protocol.setMinSpareThreads(500);
            //连接超时时间（30秒）
            protocol.setConnectionTimeout(30000);  
        }  
    } 
}