package cl.megamedia.contactos.swaggerapi.dto;

import cl.transbank.pos.responses.integrado.LastSaleResponse;
import cl.transbank.pos.responses.integrado.TotalsResponse;

public class ResponseLastSaleDTO {
	private String codigo;
	private String descripcion;
	private LastSaleResponse response;
	private TotalsResponse responseTotals;
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
	public LastSaleResponse getResponse() {
		return response;
	}
	public void setResponse(LastSaleResponse response) {
		this.response = response;
	}
	public TotalsResponse getResponseTotals() {
		return responseTotals;
	}
	public void setResponseTotals(TotalsResponse responseTotals) {
		this.responseTotals = responseTotals;
	}
	
	
	
}
