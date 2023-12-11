package com.uth.hn.controller;

import com.uth.hn.data.Reservas;

public interface ReservasInteractor {
	
	void consultarReservas();
	void crearReservas(Reservas nuevo);
	void actualizarReservas(Reservas existente);
	void eliminarReservas(String idreserva);
	void consultarPaquetesTuristicos();

}
