package com.uth.hn.views.paquetesturisticos;

import java.util.List;

import com.uth.hn.data.PaquetesTuristicos;

public interface PaquetesTuristicosViewModel {

	void mostrarPaquetesTuristicosEnGrid(List<PaquetesTuristicos> items);

	void mostrarMensajeError(String mensaje);

	void mostrarMensajeExito(String mensaje);

}
