package com.uth.hn.model;

import java.io.IOException;

import com.uth.hn.data.PaquetesTuristicos;
import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.data.Reservas;
import com.uth.hn.data.ReservasResponse;

import retrofit2.Call;
import retrofit2.Response;
import okhttp3.ResponseBody;

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
	
	public boolean crearPaquetesturisticos(PaquetesTuristicos nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabase().crearPaquetesturisticos(nuevo);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean actualizarPaquetesturisticos(PaquetesTuristicos existente) throws IOException {
		Call<ResponseBody> call = client.getDatabase().actualizarPaquetesturisticos(existente);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public boolean eliminarPaquetesturisticos(Integer id) throws IOException {
		Call<ResponseBody> call = client.getDatabase().eliminarPaquetesturisticos(id);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
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
	
	public boolean crearReservas(Reservas nuevo) throws IOException {
		Call<ResponseBody> call = client.getDatabase().crearReservas(nuevo);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
	}

	public boolean actualizarReservas(Reservas existente) throws IOException {
		Call<ResponseBody> call = client.getDatabase().actualizarReservas(existente);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
	}
	
	public boolean eliminarReservas(String idreserva) throws IOException {
		Call<ResponseBody> call = client.getDatabase().eliminarReservas(idreserva);
		Response<ResponseBody> response = call.execute();//AQUI SE LLAMA A LA BASE DE DATOS
		return response.isSuccessful();
	}
}
