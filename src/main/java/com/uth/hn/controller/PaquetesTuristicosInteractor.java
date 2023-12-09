package com.uth.hn.controller;

import com.uth.hn.data.PaquetesTuristicos;

public interface PaquetesTuristicosInteractor {

	void consultarPaquetesturisticos();
	void crearPaquetesturisticos(PaquetesTuristicos nuevo);
	void actualizarPaquetesturisticos(PaquetesTuristicos existente);
	void eliminarPaquetesturisticos(Integer id);
	
}
