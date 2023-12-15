package com.uth.hn.data;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class PaquetesReport implements JRDataSource {
	
	private List<PaquetesTuristicos> paquetes;
	private int counter = -1;
	private int maxCounter = 0;
	
	public List<PaquetesTuristicos> getPaquetes() {
		return paquetes;
	}

	public void setPaquetes(List<PaquetesTuristicos> paquetes) {
		this.paquetes = paquetes;
		this.maxCounter = this.paquetes.size()-1;
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
		if("nombre".equals(jrField.getName())) {
			return paquetes.get(counter).getNombre();
		}else if("destino".equals(jrField.getName())) {
			return paquetes.get(counter).getDestino();
		}else if("precio".equals(jrField.getName())) {
			return paquetes.get(counter).getPrecio();
		}else if("duracion".equals(jrField.getName())) {
			return paquetes.get(counter).getDuracion();
		}else if("cupo".equals(jrField.getName())) {
			return paquetes.get(counter).getCupo();
		}
		
		return "";
	}

}
