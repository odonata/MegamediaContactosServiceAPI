package cl.megamedia.contactos.swaggerapi.dto;

import cl.transbank.pos.responses.integrado.CloseResponse;

public class ResponseClosePosDTO {
	private String 			 codigo;
	private String 			 descripcion;
	private CloseResponse 	 closeResponse;
	private boolean			 pollResponse;
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
	public CloseResponse getCloseResponse() {
		return closeResponse;
	}
	public void setCloseResponse(CloseResponse closeResponse) {
		this.closeResponse = closeResponse;
	}
	public boolean isPollResponse() {
		return pollResponse;
	}
	public void setPollResponse(boolean pollResponse) {
		this.pollResponse = pollResponse;
	}
	
	
	
}
