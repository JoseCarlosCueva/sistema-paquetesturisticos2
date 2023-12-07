package com.uth.hn.data;

public class PaquetesTuristicos {
	private Integer idpaquete;
	private String nombre;
    private String destino;
    private Double precio;
    private String descripcion;
    private String duracion;
    private String cupo;
    
    public Integer getIdpaquete() {
		return idpaquete;
	}
	public void setIdpaquete(Integer idpaquete) {
		this.idpaquete = idpaquete;
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
	
    public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
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
	public void setCupo(String cupo) {
		this.cupo = cupo;
	}
}