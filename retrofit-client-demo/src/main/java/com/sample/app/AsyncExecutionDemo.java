package com.sample.app;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.dto.Employee;
import com.sample.app.interfaces.EmployeeCRUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AsyncExecutionDemo {
	private static final String BASE_URL = "http://localhost:8080/api/v1/";

	public static void main(String args[]) throws IOException {

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		EmployeeCRUD empCrud = retrofit.create(EmployeeCRUD.class);

		Call<List<Employee>> call = empCrud.all();

		call.enqueue(new Callback<List<Employee>>() {

			public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
				List<Employee> emps = response.body();
				for (Employee emp : emps) {
					System.out.println(emp);
				}

			}

			public void onFailure(Call<List<Employee>> call, Throwable t) {
				t.printStackTrace();
			}

		});

		System.out.println("Task submit to get all the employees information");

	}

}