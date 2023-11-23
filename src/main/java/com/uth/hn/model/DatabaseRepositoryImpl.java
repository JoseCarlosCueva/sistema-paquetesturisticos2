package com.uth.hn.model;

import java.io.IOException;

import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.data.ReservasResponse;

import retrofit2.Call;
import retrofit2.Response;

public class DatabaseRepositoryImpl {
	
	private static DatabaseRepositoryImpl INSTANCE;
	private DatabaseClient client;
	
	private DatabaseRepositoryImpl(String url, Long timeout) {
		client = new DatabaseClient(url, timeout);
	}
	
	public static DatabaseRepositoryImpl getInstance(String url, Long timeout) {
		if(INSTANCE == null) {
			synchronized (DatabaseRepositoryImpl.class) {
				if(INSTANCE == null) {
					INSTANCE = new DatabaseRepositoryImpl(url, timeout);
				}
			}
		}
		return INSTANCE;
	}
	
	public PaquetesturisticosResponse consultarPaquetesturisticos() throws IOException {
		Call<PaquetesturisticosResponse> call = client.getDatabase().consultarPaquetesturisticos();
		Response<PaquetesturisticosResponse> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}

	public ReservasResponse consultarReservas() throws IOException {
		Call<ReservasResponse> call = client.getDatabase().consultarReservas();
		Response<ReservasResponse> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		if(response.isSuccessful()) {
			return response.body();
		}else {
			return null;
		}
	}
}
