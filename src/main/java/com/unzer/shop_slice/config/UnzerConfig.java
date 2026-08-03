package com.unzer.shop_slice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.unzer.payment.Unzer;

@Configuration
@Profile("unzer")
public class UnzerConfig {

    @Value("${unzer.private-key:}")
    private String privateKey;

    @Bean
    public Unzer unzerWebClient() {
        return new Unzer(privateKey);
    }

}
