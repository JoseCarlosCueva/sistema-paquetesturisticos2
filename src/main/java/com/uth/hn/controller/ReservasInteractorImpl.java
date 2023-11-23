package com.uth.hn.controller;

import java.io.IOException;

import com.uth.hn.data.ReservasResponse;
import com.uth.hn.model.DatabaseRepositoryImpl;
import com.uth.hn.views.reservas.ReservasViewModel;

public class ReservasInteractorImpl implements ReservasInteractor {
	
	private DatabaseRepositoryImpl modelo;
	private ReservasViewModel vista;
	
	public ReservasInteractorImpl(ReservasViewModel view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}
	
	@Override
	public void consultarReservas() {
		try {
			ReservasResponse respuesta = this.modelo.consultarReservas();
			if(respuesta == null || respuesta.getItems() == null) {
			this.vista.mostrarMensajeError("No hay reservas");
		}else {
			this.vista.mostrarReservasEnGrid(respuesta.getItems());
		}
		}catch(IOException e) {
			e.printStackTrace();
		}	
		}

}
