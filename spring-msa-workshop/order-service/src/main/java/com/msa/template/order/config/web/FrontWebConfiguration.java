package com.msa.template.order.config.web;

import com.msa.template.core.binder.LocalDateParamBinder;
import com.msa.template.core.binder.LocalDateTimeParamBinder;
import com.msa.template.core.binder.LocalTimeParamBinder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class FrontWebConfiguration implements WebMvcConfigurer {

	private final MessageSource messageSource;
	private final MappingJackson2HttpMessageConverter adminMappingJackson2HttpMessageConverter;


	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(adminMappingJackson2HttpMessageConverter);
	}

	@Override
	public Validator getValidator() {
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		factoryBean.setValidationMessageSource(messageSource);
		return factoryBean;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new LocalDateParamBinder());
		registry.addConverter(new LocalTimeParamBinder());
		registry.addConverter(new LocalDateTimeParamBinder());
	}
}
