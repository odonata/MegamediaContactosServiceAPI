package cl.megamedia.contactos.to;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa los datos de Area de negocio que agrupan a los clientes
 * @author Gonzalo Silva
 *
 */
public class Area {
	
	private int id;
    private String nombre;
    private String creacion;
    
    @JsonProperty("fecha_actualizacion")
    private String fechaActualizacion;
    
    private String usuario;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCreacion() {
		return creacion;
	}
	public void setCreacion(String creacion) {
		this.creacion = creacion;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
    
    

}
