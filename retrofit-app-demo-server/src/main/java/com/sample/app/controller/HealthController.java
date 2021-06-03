package com.sample.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/v1/health")
@Api(tags = "Health controller version 1", description = "This section contains health related APIs")
public class HealthController {

	@ApiOperation(value = "Get the health of service", notes = "This API will get the health of service")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity health() {
		return ResponseEntity.ok().build();
	}

}
