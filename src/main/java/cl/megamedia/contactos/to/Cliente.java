package cl.megamedia.contactos.to;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa los datos de Cliente relacionado a un área de negocio en particular
 * @author Gonzalo Silva
 *
 */
public class Cliente {
	
	@JsonProperty("cliente_id")
    private int clienteId;
    
    @JsonProperty("area_id")
    private int areaId;
    
    @JsonProperty("nombre_cliente")
    private String nombreCliente;
    
    @JsonProperty("email_contacto")
    private String emailContacto;
    
    @JsonProperty("fono_contacto")
    private String fonoContacto;
    
    private String creacion;
    
    @JsonProperty("fecha_actualizacion")
    private String fechaActualizacion;
    
    private String usuario;
    
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
	public String getEmailContacto() {
		return emailContacto;
	}
	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}
	public String getFonoContacto() {
		return fonoContacto;
	}
	public void setFonoContacto(String fonoContacto) {
		this.fonoContacto = fonoContacto;
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
