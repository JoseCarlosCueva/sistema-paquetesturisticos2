package com.uth.hn.data;

import java.time.LocalDate;
import java.util.Date;

public class Reservas {
	private String idreserva;
	private String paquete;
    private String cliente;
    private Date fecha;
    private Double precio;
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
	
	
    public Date  getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
    public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
		 
	public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
	
 
}
