package com.sample.app.interfaces;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HealthCRUD {
	@GET("health")
	Call<Void> health();
}
