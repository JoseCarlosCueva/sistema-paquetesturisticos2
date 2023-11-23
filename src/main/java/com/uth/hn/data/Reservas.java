package com.uth.hn.data;

import java.time.LocalDate;

public class Reservas {
	private String idreserva;
	private String paquete;
    private String cliente;
    private LocalDate fecha;
    private String precio;
    private String estado;
    
    
    public String getIdreserva() {
		return idreserva;
	}
	public void setIdreserva(String idreserva) {
		this.idreserva = idreserva;
	}
	public String getPaquete() {
		return paquete;
	}
	public void setPaquete(String paquete) {
		this.paquete = paquete;
	}
	
    public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}	
	
	
    public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
    public String getPrecio() {
		return precio;
	}
	public void setFecha(String precio) {
		this.precio = precio;
	}
		
	public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
	
 
}
