package com.uth.hn.model;


import com.uth.hn.data.PaquetesTuristicos;
import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.data.ReservasResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DatabaseRepository {

	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/jose202210010792/paquetesturisticos/paquetesturisticos")
	Call<PaquetesturisticosResponse> consultarPaquetesturisticos();
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/jose202210010792/paquetesturisticos/paquetesturisticos")
	Call<ResponseBody> crearPaquetesturisticos(@Body PaquetesTuristicos nuevo);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/jose202210010792/paquetesturisticos/paquetesturisticos")
	Call<ResponseBody> actualizarPaquetesturisticos(@Body PaquetesTuristicos existente);
	
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/jose202210010792/paquetesturisticos/reservas")
	Call<ReservasResponse> consultarReservas();
	
}
