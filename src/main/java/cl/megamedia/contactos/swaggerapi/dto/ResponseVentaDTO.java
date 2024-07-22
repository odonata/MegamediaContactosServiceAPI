package cl.megamedia.contactos.swaggerapi.dto;

import cl.transbank.pos.responses.common.LoadKeysResponse;
import cl.transbank.pos.responses.common.RefundResponse;
import cl.transbank.pos.responses.integrado.SaleResponse;

public class ResponseVentaDTO {
	private String 			 codigo;
	private String 			 descripcion;
	private SaleResponse 	 saleResponse;
	private RefundResponse 	 refundResponse;
	
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
	public SaleResponse getSaleResponse() {
		return saleResponse;
	}
	public void setSaleResponse(SaleResponse saleResponse) {
		this.saleResponse = saleResponse;
	}
	public RefundResponse getRefundResponse() {
		return refundResponse;
	}
	public void setRefundResponse(RefundResponse refundResponse) {
		this.refundResponse = refundResponse;
	}

	
	

}
