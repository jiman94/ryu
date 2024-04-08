package com.msa.template.inventory.config.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@RequiredArgsConstructor
@Configuration
public class MessageConverterConfiguration {

    private final ObjectMapper objectMapper;

    @Bean(name = "adminMappingJackson2HttpMessageConverter")
    public MappingJackson2HttpMessageConverter adminMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);
        return converter;
    }
}
