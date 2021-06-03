package com.sample.app.controller;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.app.dto.EmployeeDto;
import com.sample.app.model.Employee;
import com.sample.app.util.EmployeeUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("api/v1/employees")
@Api(tags = "Employee controller version 1", description = "This section contains employee related APIs")
public class EmployeeController {

	@ApiOperation(value = "Get all the employees", notes = "This API will get details of all the employees")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> all() {
		sleep(2);
		return ResponseEntity.ok(EmployeeUtil.all());
	}

	@ApiOperation(value = "Get all the employees contain this firstName", notes = "This API will get details of all the employees contains this firstName")
	@RequestMapping(value = "/by-first-name", method = RequestMethod.GET)
	public ResponseEntity<Collection<Employee>> byFirstName(
			@ApiParam(name = "firstName", value = "firstName", required = true) @RequestParam("firstName") String firstName) {
		return ResponseEntity.ok(EmployeeUtil.containsFirstName(firstName));
	}

	@ApiOperation(value = "Create new employees", notes = "This API will create new employee")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> create(
			@ApiParam(name = "emp", value = "employee details to create", required = true) @RequestBody EmployeeDto emp) {
		return new ResponseEntity<>(EmployeeUtil.create(emp), HttpStatus.CREATED);

	}

	@ApiOperation(value = "Get employee by id", notes = "This API will get employee by id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> byId(
			@ApiParam(name = "id", value = "Employee id", required = true) @PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(emp);

	}

	@ApiOperation(value = "Delete an employee", notes = "This API will delete an employee")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorization", value = "SSO token in format: \n\rBearer eyJraWQiOiJhZj", dataType = "String", paramType = "header", required = true) })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Employee> deleteById(
			@ApiParam(name = "id", value = "Employee id", required = true) @PathVariable int id) {
		Employee emp = EmployeeUtil.byId(id);

		if (emp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.delete(id));
	}

	@ApiOperation(value = "Update employee by id", notes = "This API will update the employee by id")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateById(
			@ApiParam(name = "id", value = "Employee id", required = true) @PathVariable int id,
			@ApiParam(name = "emp", value = "employee details to update", required = true) @RequestBody EmployeeDto emp) {

		Employee currentEmp = EmployeeUtil.byId(id);

		if (currentEmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return ResponseEntity.ok(EmployeeUtil.updateById(id, emp));
	}
	
	private static void sleep(int noOfSeconds) {
		try {
			TimeUnit.SECONDS.sleep(noOfSeconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
