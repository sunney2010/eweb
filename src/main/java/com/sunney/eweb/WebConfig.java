/*
 * Copyright 1999-2024 Colotnet.com All right reserved. This software is the confidential and proprietary information of
 * Colotnet.com ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with Colotnet.com.
 */
package com.sunney.eweb;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sunney.eweb.config.MappingFastjson2HttpMessageConverter;

/**
 * 类WebConfig.java的实现描述：TODO 类实现描述
 * 
 * @author Sunney 2016年11月1日 下午7:47:06
 */
@EnableWebMvc
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "HEAD", "POST", "PUT", "PATCH", "DELETE",
                                                                      "OPTIONS", "TRACE")
                                                                      // .allowedHeaders("*")
                                                                      // .exposedHeaders("*")
        .allowCredentials(true).maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/",
                                                                "classpath:/resources/", "classpath:/static/",
                                                                "classpath:/public/", "classpath:/templates/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/login.html");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //clear后自定义转换器才生效
        converters.clear();
        converters.add(mappingFastjson2HttpMessageConverter());

    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    }

    @Bean
    public MappingFastjson2HttpMessageConverter mappingFastjson2HttpMessageConverter() {
        SerializerFeature[] serializerFeature = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
                                                                          SerializerFeature.WriteNullListAsEmpty,
                                                                          SerializerFeature.QuoteFieldNames,
                                                                          SerializerFeature.WriteDateUseDateFormat};
        MappingFastjson2HttpMessageConverter mappingFastjson = new MappingFastjson2HttpMessageConverter();
        mappingFastjson.setSerializerFeature(serializerFeature);
        return mappingFastjson;

    }

    @Bean
    public DateFormatter dateFormatter() {
        return new DateFormatter();
    }
}
