package io.igorcossta.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class Beans {
    @Value("${api.calories}")
    private String API_KEY;

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public WebClient webClient(ObjectMapper objectMapper) {
        ObjectMapper copied = objectMapper.copy();
        copied.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(conf -> {
                    conf.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(copied));
                }).build();

        return WebClient.builder()
                .baseUrl("https://api.calorieninjas.com/v1/nutrition")
                .defaultHeader("X-Api-Key", API_KEY)
                .exchangeStrategies(exchangeStrategies)
                .build();
    }
}
