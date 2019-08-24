package com.weatherapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by alpa on 2019-08-22
 */
@Configuration
public class ConfigurationWebClient {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
