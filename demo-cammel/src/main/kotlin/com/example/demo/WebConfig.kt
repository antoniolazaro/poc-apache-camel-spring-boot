package com.example.demo

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

@Configuration
class WebConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }

//    @Bean
//    fun webClient(): WebClient {
//        return WebClient.
//                .builder()
//                .baseUrl("http://localhost:8080/demo")
//                .defaultCookie("cookieKey", "cookieValue")
//                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//                .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080/demo"))
//                .build()
//    }


}