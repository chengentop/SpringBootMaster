package com.master.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.master.core.annotation.SessionParamMethodArgumentResolver;
import com.master.core.framework.web.exception.SystemExceptionResolver;
import com.master.core.framework.web.interceptor.LogInterceptor;
import com.master.core.framework.web.interceptor.UserSecurityInterceptor;
import com.master.core.framework.web.view.JsonViewResolver;


@SpringBootConfiguration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan({"com.master.business.controller"})
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new SessionParamMethodArgumentResolver());
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new UserSecurityInterceptor())
		.addPathPatterns("/*/console/**");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/favicon/**").addResourceLocations("/favicon/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico");
		registry.addResourceHandler("/robots.txt").addResourceLocations("/robots.txt");
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		Map<String, MediaType> mediaTypes = new HashMap<>();
		mediaTypes.put("json", MediaType.APPLICATION_JSON);
		mediaTypes.put("xml", MediaType.APPLICATION_XML);
		mediaTypes.put("html", MediaType.TEXT_HTML);
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML).mediaTypes(mediaTypes);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		List<ViewResolver> viewResolvers = new LinkedList<>();

		viewResolvers.add(new JsonViewResolver());
		InternalResourceViewResolver jspViewResolver = new InternalResourceViewResolver();
		viewResolvers.add(jspViewResolver);

		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(viewResolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}
	@Bean
	public Charset MyStringHttpMessageConverter() {
		return new StringHttpMessageConverter(Charset.forName("UTF-8")).getDefaultCharset();
	}

	@Bean
	public HandlerExceptionResolver systemExceptionResolver() {
		return new SystemExceptionResolver();
	}
	
	@Bean 
	public LogInterceptor logInterceptor(){
		return new LogInterceptor();
	}
	
	/** 文件上传支持 **/
	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(100 * 1024 * 1024);
		multipartResolver.setDefaultEncoding(StandardCharsets.UTF_8.toString());
		return multipartResolver;
	}	
}
