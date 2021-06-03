package com.sample.app;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.interfaces.EmployeeCRUD;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RawResponseDemo {
	private static final String BASE_URL = "http://localhost:8080/api/v1/";

	private static void printAllEmployees(Retrofit retrofit) throws IOException {
		EmployeeCRUD empCrud = retrofit.create(EmployeeCRUD.class);

		Call<ResponseBody> call = empCrud.allEmployeesRawResponse();

		Response<ResponseBody> empsResponse = call.execute();

		ResponseBody respBody = empsResponse.body();

		String respString = respBody.string();

		System.out.println(respString);

	}

	public static void main(String args[]) throws IOException {

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		printAllEmployees(retrofit);

	}

}