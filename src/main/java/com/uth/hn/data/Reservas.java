package com.uth.hn.data;

import java.time.LocalDate;

public class Reservas {
	private String idReserva;
	private String numero;
    private String destino;
    private LocalDate precio;
    private String descripcion;
    private String fechaInicio;
    
    
    public String getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(String idReserva) {
		this.idReserva = idReserva;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
    public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}	
	
	
    public LocalDate getPrecio() {
		return precio;
	}
	public void setPrecio(LocalDate precio) {
		this.precio = precio;
	}
	
	
    public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
	public String getFechaInicio() {
        return fechaInicio;
    }
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
	
 
}
