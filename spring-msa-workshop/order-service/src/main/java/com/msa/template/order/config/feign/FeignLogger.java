package com.msa.template.order.config.feign;

import com.google.gson.Gson;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
public class FeignLogger {

	public static void request(Request request) {
		Gson gson = new Gson();
		Map<String, Object> log = new LinkedHashMap<>();

		log.put("type", "request");
		log.put("method", request.httpMethod());
		log.put("url", request.url());
		log.put("header", request.headers());
		if (request.body() != null
				&& request.body().length > 0
				&& request.headers().get("Content-Type").contains("application/json")) {
			log.put("body", toString(request.body(), request.charset()));
		}
		FeignLogger.log.info(gson.toJson(log));
	}

	public static void response(Response response) {
		Request request = response.request();
		Gson gson = new Gson();
		Map<String, Object> log = new LinkedHashMap();

		log.put("type", "response");
		log.put("method", request.httpMethod());
		log.put("status", response.status());
		log.put("url", request.url());
		log.put("header", response.headers());

		FeignLogger.log.info(gson.toJson(log));
	}

	public static String toString(byte[] body, Charset charset) {
		return Objects.isNull(charset) ? "Empty Charset" : new String(body, charset);
	}
}
