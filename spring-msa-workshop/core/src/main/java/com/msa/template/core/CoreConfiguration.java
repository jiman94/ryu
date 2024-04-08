package com.msa.template.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"com.msa.template.core"})
@Import({})
public class CoreConfiguration {
}
