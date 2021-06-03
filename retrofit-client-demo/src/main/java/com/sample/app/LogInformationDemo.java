package com.sample.app;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.dto.Employee;
import com.sample.app.interfaces.EmployeeCRUD;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInformationDemo {
	private static final String BASE_URL = "http://localhost:8080/api/v1/";

	private static void printAllEmployees(EmployeeCRUD empCrud, Retrofit retrofit) throws IOException {

		Call<List<Employee>> call = empCrud.all();

		Response<List<Employee>> empsResponse = call.execute();

		List<Employee> empsList = empsResponse.body();

		System.out.println("All employees");
		for (Employee emp : empsList) {
			System.out.println(emp);
		}
		
		System.out.println("\n");
	}

	private static void printById(EmployeeCRUD empCrud, Integer id) throws IOException {
		Call<Employee> call = empCrud.byId(id);

		Response<Employee> empsResponse = call.execute();

		Employee emp = empsResponse.body();

		System.out.println("\nEmployee with id " + id + " information : " + emp);
	}

	public static void main(String args[]) throws IOException {

		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		okhttp3.OkHttpClient client = new okhttp3.OkHttpClient().newBuilder().addInterceptor(interceptor).build();

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).client(client)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		EmployeeCRUD empCrud = retrofit.create(EmployeeCRUD.class);

		printAllEmployees(empCrud, retrofit);

		printById(empCrud, 1);

	}

}