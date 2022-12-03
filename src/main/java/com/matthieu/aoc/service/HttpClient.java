package com.matthieu.aoc.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class HttpClient {
	
	private OkHttpClient client;
	
	public HttpClient() {
		client = new OkHttpClient();
	}

	public String get(String url, Headers headers) throws IOException {
		Request request = new Request.Builder()
								      .url(url)
								      .headers(headers)
								      .build();

	    Call call = client.newCall(request);
	    Response response = call.execute();
	    
		return response.body().string();
	}
}
