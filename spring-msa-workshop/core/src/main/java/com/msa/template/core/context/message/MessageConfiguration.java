package com.msa.template.core.context.message;

import com.msa.template.core.context.ApplicationContextProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MessageConfiguration {

	private static final List<String> MESSAGE_SOURCE_CLASSPATH_LIST =
		List.of("classpath:messages/message");

	public static MessageSource getMessageSource() {
		return ApplicationContextProvider.getApplicationContext().getBean(MessageSource.class);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
			new ReloadableResourceBundleMessageSource();

		try {
			messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());

			for (String path : MESSAGE_SOURCE_CLASSPATH_LIST) {
				messageSource.addBasenames(path);
			}
		} catch (Exception e) {
			// exception will be ignored
			log.error("Failed to initialize message source", e);
		}

		return messageSource;
	}
}
