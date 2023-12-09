package com.uth.hn.model;


import com.uth.hn.data.PaquetesTuristicos;
import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.data.Reservas;
import com.uth.hn.data.ReservasResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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
	@DELETE("/pls/apex/jose202210010792/paquetesturisticos/paquetesturisticos")
	Call<ResponseBody> eliminarPaquetesturisticos(@Query("id")Integer id);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@GET("/pls/apex/jose202210010792/paquetesturisticos/reservas")
	Call<ReservasResponse> consultarReservas();
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@POST("/pls/apex/jose202210010792/paquetesturisticos/reservas")
	Call<ResponseBody> crearReservas(@Body Reservas nuevo);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@PUT("/pls/apex/jose202210010792/paquetesturisticos/reservas")
	Call<ResponseBody> actualizarReservas(@Body Reservas existente);
	
	@Headers({
	    "Accept: application/json",
	    "User-Agent: Retrofit-Sample-App"
	})
	@DELETE("/pls/apex/jose202210010792/paquetesturisticos/reservas")
	Call<ResponseBody> eliminarReservas(@Query("id")String id);
	

	
}
