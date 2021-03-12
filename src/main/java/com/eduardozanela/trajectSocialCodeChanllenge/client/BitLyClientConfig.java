package com.eduardozanela.trajectSocialCodeChanllenge.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class BitLyClientConfig {
	
	@Value("${client.bitly.token}")
	private String accessToken;

    @Bean
    public RequestInterceptor bearerTokenRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                template.header("Authorization",
                        String.format("Bearer %s", accessToken));
            }
        };
    }
}