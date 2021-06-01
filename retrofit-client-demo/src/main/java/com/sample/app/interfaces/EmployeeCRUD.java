package com.sample.app.interfaces;

import java.util.List;

import com.sample.app.dto.Employee;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EmployeeCRUD {
	
	@GET("employees")
	Call<List<Employee>> all();
	
	@GET("employees/{id}")
	Call<Employee> byId(@Path("id") Integer empId);
	
	@GET("employees/by-first-name")
	Call<List<Employee>> containsFirstName(@Query("firstName") String firstName);

}
