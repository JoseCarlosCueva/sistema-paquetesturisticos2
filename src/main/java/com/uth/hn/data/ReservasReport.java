package com.uth.hn.data;

import java.util.List;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

public class ReservasReport implements JRDataSource {
	
	private List<Reservas> reservas;
	private int counter = -1;
	private int maxCounter = 0;
	
	public List<Reservas> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reservas> reservas) {
		this.reservas = reservas;
		this.maxCounter = this.reservas.size()-1;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public int getMaxCounter() {
		return maxCounter;
	}

	public void setMaxCounter(int maxCounter) {
		this.maxCounter = maxCounter;
	}

	@Override
	public boolean next() throws JRException {
		if(counter < maxCounter) {
			counter++;
			return true;
		}
		return false;
	}

	@Override
	public Object getFieldValue(JRField jrField) throws JRException {
		if("numero".equals(jrField.getName())) {
			return reservas.get(counter).getIdreserva();
		}else if("paquete".equals(jrField.getName())) {
			return reservas.get(counter).getPaquete();
		}else if("cliente".equals(jrField.getName())) {
			return reservas.get(counter).getCliente();
		}else if("precio".equals(jrField.getName())) {
			return reservas.get(counter).getPrecio();
		}else if("estado".equals(jrField.getName())) {
			return reservas.get(counter).getEstado();
		}
		
		return "";
	}
	
}
