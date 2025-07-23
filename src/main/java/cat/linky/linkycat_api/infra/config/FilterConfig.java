package cat.linky.linkycat_api.infra.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.linky.linkycat_api.infra.filter.RequestLoggerFilter;

@Configuration
public class FilterConfig {
    
    @Bean
    public FilterRegistrationBean<RequestLoggerFilter> requestLoggerFilter() {

        FilterRegistrationBean<RequestLoggerFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new RequestLoggerFilter());
        registration.addUrlPatterns("/*");
        registration.setOrder(1);
        
        return registration;
    }
}
