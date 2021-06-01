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

public class PutRequestExample {
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

	private static void updateEmployee(EmployeeCRUD empCrud, Employee emp) throws IOException {
		Call<Employee> call = empCrud.updateEmployee(emp.getId(), emp);

		Response<Employee> empResp = call.execute();

		Employee updatedEmp = empResp.body();

		System.out.println("\nUpdated employee details : " + updatedEmp);

	}

	private static Employee getById(EmployeeCRUD empCrud, Integer id) throws IOException {
		Call<Employee> call = empCrud.byId(id);

		Response<Employee> empsResponse = call.execute();

		return empsResponse.body();

	}

	public static void main(String args[]) throws IOException {

		Gson gson = new GsonBuilder().setLenient().create();

		Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create(gson)).build();

		EmployeeCRUD empCrud = retrofit.create(EmployeeCRUD.class);

		printAllEmployees(empCrud);

		Employee empWithId1 = getById(empCrud, 1);

		empWithId1.setFirstName("Joel");
		empWithId1.setLastName("Chelli");

		updateEmployee(empCrud, empWithId1);

		printAllEmployees(empCrud);
	}

}


