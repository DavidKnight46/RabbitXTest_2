package org.rabbitx.rabbitbetest.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${margin.limit}")
    private Integer marginLimit;

    @Value("${margin.leverage}")
    private Integer leverage;

    @Bean
    public Integer getLimit(){
        return marginLimit;
    }

    @Bean
    public Integer getLeverage(){
        return leverage;
    }
}
