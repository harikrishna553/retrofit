package com.sample.app.interfaces;

import java.util.List;

import com.sample.app.dto.Employee;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeCRUD {
	
	@GET("employees")
	Call<List<Employee>> all();

}
