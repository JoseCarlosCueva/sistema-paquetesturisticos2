package com.uth.hn.model;


import com.uth.hn.data.PaquetesturisticosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface DatabaseRepository {

	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/jose202210010792/paquetesturisticos/paquetesturisticos")
	Call<PaquetesturisticosResponse> consultarPaquetesturisticos();
	
}
