package com.uth.hn.controller;

import java.io.IOException;

import com.uth.hn.data.PaquetesturisticosResponse;
import com.uth.hn.data.Reservas;
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

	@Override
	public void crearReservas(Reservas nuevo) {
		try {
			boolean creado = this.modelo.crearReservas(nuevo);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Reservas creado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al crear la Reserva");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizarReservas(Reservas existente) {
		try {
			boolean creado = this.modelo.actualizarReservas(existente);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Reservas  modificado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al modificar la Reserva");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarReservas(String idreserva) {
		try {
			boolean creado = this.modelo.eliminarReservas(idreserva);
			if(creado == true) {
				this.vista.mostrarMensajeExito("Reservas borrado exitosamente");
		}else {
			this.vista.mostrarMensajeError("Hubo un problema al borrar la Reserva");
		}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void consultarPaquetesTuristicos() {
		try {
			PaquetesturisticosResponse respuesta = this.modelo.consultarPaquetesturisticos();
			if(respuesta != null && respuesta.getItems() != null) {
				this.vista.llenarComboboxPaquetesTuristicos(respuesta.getItems());
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
