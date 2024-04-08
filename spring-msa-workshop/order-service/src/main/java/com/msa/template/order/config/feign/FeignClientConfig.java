package com.msa.template.order.config.feign;

import feign.Client;
import feign.RetryableException;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Slf4j
@RequiredArgsConstructor
@EnableFeignClients(basePackages = "com.msa.template")
@Configuration
public class FeignClientConfig {

	public static final long RETRY_PERIOD = 100;
	public static final long RETRY_MAX_PERIOD = 2000;
	public static final int RETRY_MAX_ATTEMPTS = 3;

	@Bean
	public Retryer retryer() {
		return new Retryer.Default(RETRY_PERIOD, RETRY_MAX_PERIOD, RETRY_MAX_ATTEMPTS);
	}

	@Bean
	public Client client() {
		return new FeignCustomClient(null, null);
	}

	@Bean
	public ErrorDecoder ErrorDecoder() {
		return (methodKey, response) -> {
			FeignLogger.response(response);

			int httpStatus = response.status();

			if (HttpStatus.valueOf(httpStatus).is5xxServerError()) {
				return new RetryableException(
					httpStatus,
					response.reason(),
					response.request().httpMethod(),
					null,
					response.request());
			} else if (HttpStatus.valueOf(httpStatus).is4xxClientError()) {
				// retry x
			}

			return new ErrorDecoder.Default().decode(methodKey, response);
		};
	}
}
