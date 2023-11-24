package com.uth.hn.data;

public class PaquetesTuristicos {
	private Integer idPaquete;
	private String nombre;
    private String destino;
    private String precio;
    private String descripcion;
    private String duracion;
    private String cupo;
    
    public Integer getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(Integer idPaquete) {
		this.idPaquete = idPaquete;
	}
	
    public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		
    public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

    public String getCupo() {
		return cupo;
	}
	public void setCupoPersonas(String cupoPersonas) {
		this.cupo = cupoPersonas;
	}
}