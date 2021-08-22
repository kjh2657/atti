package com.atti.aroo.support.config;


import com.atti.aroo.support.filter.FirstFilter;
import com.atti.aroo.support.filter.SecondFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<FirstFilter> firstFilter(){
        return apiFilter(new FirstFilter(), 1);
    }
    @Bean
    public FilterRegistrationBean<SecondFilter> secondFilter(){
        return apiFilter(new SecondFilter(), 2);
    }

    private <T extends Filter> FilterRegistrationBean<T> apiFilter(T filter, int order){
        FilterRegistrationBean<T> registrationBean = new FilterRegistrationBean<>(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(order);
        return registrationBean;
    }
}
