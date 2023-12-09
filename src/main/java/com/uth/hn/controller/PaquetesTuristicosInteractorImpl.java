package com.uth.hn.controller;

import java.io.IOException;

import com.uth.hn.data.PaquetesTuristicos;
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

	@Override
	public void crearPaquetesturisticos(PaquetesTuristicos nuevo) {
		try {
			boolean creado = this.modelo.crearPaquetesturisticos(nuevo);
			if(creado == true) {
				this.vista.mostrarMensajeExito("PaqueteTuristito creado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al crear el PaqueteTuristito");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarPaquetesturisticos(PaquetesTuristicos existente) {
		try {
			boolean creado = this.modelo.actualizarPaquetesturisticos(existente);
			if(creado == true) {
				this.vista.mostrarMensajeExito("PaqueteTuristito modificado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al modificar el PaqueteTuristito");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarPaquetesturisticos(Integer id) {
		try {
			boolean creado = this.modelo.eliminarPaquetesturisticos(id);
			if(creado == true) {
				this.vista.mostrarMensajeExito("PaqueteTuristito borrado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al borrar el PaqueteTuristito");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}


}
