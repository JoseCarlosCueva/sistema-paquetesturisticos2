package com.uth.hn.controller;

import java.io.IOException;

import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.model.DatabaseRepositoryImpl;
import com.uth.hn.views.paquetesturisticos.PaquetesTuristicosViewModel;

public class PaquetesTuristicosInteractorImpl implements PaquetesTuristicosInteractor {

	private DatabaseRepositoryImpl modelo;
	private PaquetesTuristicosViewModel vista;
	
	public PaquetesTuristicosInteractorImpl(PaquetesTuristicosViewModel view) {
		super();
		this.vista = view;
		this.modelo = DatabaseRepositoryImpl.getInstance("https://apex.oracle.com", 30000L);
	}
	
	@Override
	public void consultarPaquetesturisticos() {
		try {
			PaquetesturisticosResponse respuesta = this.modelo.consultarPaquetesturisticos();
			if(respuesta == null || respuesta.getItems() == null) {
			this.vista.mostrarMensajeError("No hay paquetes turisticos");
		}else {
			this.vista.mostrarPaquetesTuristicosEnGrid(respuesta.getItems());
		}
		}catch(IOException e) {
			e.printStackTrace();
		}	
		}

}
