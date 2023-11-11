package com.uth.hn.data;

import java.time.LocalDate;

public class PaquetesTuristicos {
	private Integer idPaquete;
	private String nombrePaquete;
    private String destino;
    private String precio;
    private String descripcion;
    private LocalDate fechaInicio;
    private LocalDate fechaFinalizacion;
    private String cupoPersonas;
    private boolean important;
    private Integer cupo;
    
    public Integer getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(Integer idPaquete) {
		this.idPaquete = idPaquete;
	}
	
    public String getNombrePaquete() {
		return nombrePaquete;
	}
	public void setNombrePaquete(String nombrePaquete) {
		this.nombrePaquete = nombrePaquete;
	}
	
	
    public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}	
	
    public String getPrecio() {
		return precio;
	}
	public void setPrecio(String precio) {
		this.precio = precio;
	}
	
	
    public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
    public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
    public LocalDate getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(LocalDate fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}

    public String getCupoPersonas() {
		return cupoPersonas;
	}
	public void setCupoPersonas(String cupoPersonas) {
		this.cupoPersonas = cupoPersonas;
	}
	/*public boolean isImportant() {
        return important;
    }
    public void setImportant(boolean important) {
        this.important = important;
    }*/
    
}