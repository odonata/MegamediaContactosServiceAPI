package cl.megamedia.contactos.swaggerapi.dto;

import cl.transbank.pos.responses.common.LoadKeysResponse;

public class ResponseDTO {
	private String 			 codigo;
	private String 			 descripcion;
	private LoadKeysResponse loadKeyResponse;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public LoadKeysResponse getLoadKeyResponse() {
		return loadKeyResponse;
	}
	public void setLoadKeyResponse(LoadKeysResponse loadKeyResponse) {
		this.loadKeyResponse = loadKeyResponse;
	}
	
	

}
