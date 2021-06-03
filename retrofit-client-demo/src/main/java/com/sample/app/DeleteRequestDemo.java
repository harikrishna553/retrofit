package com.sample.app;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sample.app.dto.Employee;
import com.sample.app.interfaces.EmployeeCRUD;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DeleteRequestDemo {
	private static final String BASE_URL = "http://localhost:8080/api/v1/";

	private static void printAllEmployees(EmployeeCRUD empCrud) throws IOException {

		Call<List<Employee>> call = empCrud.all();

		Response<List<Employee>> empsResponse = call.execute();

		List<Employee> empsList = empsResponse.body();

		System.out.println("\nAll employees");
		for (Employee emp : empsList) {
			System.out.println(emp);
		}
	}

	public static void main(String args[]) throws IOException {

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		EmployeeCRUD empCrud = retrofit.create(EmployeeCRUD.class);

		printAllEmployees(empCrud);

		Employee newEmp = new Employee();
		newEmp.setFirstName("Krishna");
		newEmp.setLastName("Gurram");

		int empIdToDelete = 2;
		Call<Employee> empDeleteCall = empCrud.deleteEmployee("test_token", empIdToDelete);
		Response<Employee> empDeleteResponse = empDeleteCall.execute();
		Employee emp = empDeleteResponse.body();
		System.out.println("\nDeleted employee details : " + emp + "\n");

		printAllEmployees(empCrud);
	}

}