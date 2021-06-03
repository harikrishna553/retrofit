package com.sample.app;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.interfaces.HealthCRUD;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EmptyResponseBodyDemo {
	private static final String BASE_URL = "http://localhost:8080/api/v1/";

	public static void main(String args[]) throws IOException {

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		HealthCRUD healthCRUD = retrofit.create(HealthCRUD.class);
		Call<Void> call = healthCRUD.health();
		Response<Void> voidResponse = call.execute();
		int responseCode = voidResponse.code();

		System.out.println("Response code : " + responseCode);
	}

}