package org.example.configuration;

import org.example.filter.BasicAuthFilter;
import org.example.filter.MyResponseFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<MyResponseFilter> myResponseFilter() {
        FilterRegistrationBean<MyResponseFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MyResponseFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URL patterns
        registrationBean.setOrder(2); // Set precedence
        return registrationBean;
    }
    
    @Bean
    public FilterRegistrationBean<BasicAuthFilter> basicAuthFilter() {
        FilterRegistrationBean<BasicAuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new BasicAuthFilter());
        registrationBean.addUrlPatterns("/*"); // Apply to all URL patterns
        registrationBean.setOrder(1); // Set precedence, so it runs before MyResponseFilter
        return registrationBean;
    }
}
