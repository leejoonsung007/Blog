package com.springboot.blog.config;

import com.springboot.blog.interceptor.BackInterceptor;
import com.springboot.blog.interceptor.ForeInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    @Bean
    public HandlerInterceptor getForeInterceptor() {
        return new ForeInterceptor();
    }

    @Bean
    public HandlerInterceptor getBackInterceptor() {
        return new BackInterceptor();
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    // no need to create a controller and return page
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/toLogin").setViewName("login.html");
        super.addViewControllers(registry);
    }

    /**
     * add Interceptors for specific link
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns
        // excludePathPatterns - admin login link can be more complex
        registry.addInterceptor(getBackInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/toLogin", "/admin/login");
        registry.addInterceptor(getForeInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin", "/admin/**", "/js/**", "/css/**", "/img/**");
        super.addInterceptors(registry);
    }

}

