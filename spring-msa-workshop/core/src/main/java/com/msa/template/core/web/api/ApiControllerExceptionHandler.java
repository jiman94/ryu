package com.msa.template.core.web.api;

import com.msa.template.core.exception.PolicyViolationException;
import com.msa.template.core.exception.ResourceNotFoundException;
import com.msa.template.core.exception.UnauthorizedException;
import feign.FeignException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.NotAcceptableStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;
import software.amazon.awssdk.core.exception.SdkServiceException;


@Slf4j
@RestControllerAdvice
public class ApiControllerExceptionHandler {

	private static final String LOG_MESSAGE_FORMAT = "{} '{}' - {}";
	private static final String UNCAUGHT_LOG_MESSAGE = "??";

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PolicyViolationException.class)
	public final ApiResponse<Void> handleBadRequest(
		final PolicyViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public final ApiResponse<Void> handleBadRequest(
		final MethodArgumentTypeMismatchException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, BindingErrorResolver.resolveFrom(ex));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MissingServletRequestParameterException.class})
	public final ApiResponse<Void> handleBadRequest(
		final MissingServletRequestParameterException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, BindingErrorResolver.resolveFrom(ex));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({BindException.class, MethodArgumentNotValidException.class})
	public final ApiResponse<Void> handleBadRequest(
		final BindException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, BindingErrorResolver.resolveFrom(ex));
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public final ApiResponse<Void> handleBadRequest(
		final ConstraintViolationException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.BAD_REQUEST, BindingErrorResolver.resolveFrom(ex));
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NoHandlerFoundException.class)
	public final ApiResponse<Void> handleNotFound(
		final NoHandlerFoundException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ResourceNotFoundException.class})
	public ApiResponse<Void> handleNotFound(
		final ResourceNotFoundException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(ex.getMessage());
	}

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler({UnauthorizedException.class})
	public ApiResponse<Void> handleUnauthorized(
		final UnauthorizedException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.UNAUTHORIZED);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(NotAcceptableStatusException.class)
	public final ApiResponse<Void> handleInternalServerError(
		final NotAcceptableStatusException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(HttpServerErrorException.class)
	public final ApiResponse<Void> handleInternalServerError(
		final HttpServerErrorException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({
		FeignException.FeignClientException.class,
		FeignException.FeignServerException.class
	})
	public ApiResponse<Void> handleInternalServerError(
		final FeignException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(ResourceAccessException.class)
	public final ApiResponse<Void> handleInternalServerError(
		final ResourceAccessException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({SdkServiceException.class})
	public ApiResponse<Void> handleInternalServerError(
		final SdkServiceException ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({Exception.class})
	public ApiResponse<Void> handleInternalServerError(final Exception ex, final WebRequest request) {
		this.writeLog(ex, request);
		return ApiResponseGenerator.fail(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private <E extends Exception> void writeLog(final E ex, final WebRequest webRequest) {
		try {
			HttpServletRequest servletRequest = ((ServletWebRequest) webRequest).getRequest();
			log.error(
				LOG_MESSAGE_FORMAT,
				servletRequest.getMethod(),
				servletRequest.getRequestURI(),
				ex.getMessage(),
				ex);
		} catch (Exception e) {
			log.error(LOG_MESSAGE_FORMAT, UNCAUGHT_LOG_MESSAGE, UNCAUGHT_LOG_MESSAGE, e.getMessage(), e);
		}
	}
}
