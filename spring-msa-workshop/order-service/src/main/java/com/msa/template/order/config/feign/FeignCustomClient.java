package com.msa.template.order.config.feign;

import feign.Client;
import feign.Request;
import feign.Response;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;

@Slf4j
public class FeignCustomClient extends Client.Default {

	public FeignCustomClient(SSLSocketFactory sslContextFactory, HostnameVerifier hostnameVerifier) {
		super(sslContextFactory, hostnameVerifier);
	}

	@Override
	public Response execute(Request request, Request.Options options) throws IOException {
		FeignLogger.request(request);
		Response response = super.execute(request, options);
		FeignLogger.response(response);
		return response;
	}
}
