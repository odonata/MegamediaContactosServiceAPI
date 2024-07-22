package cl.megamedia.contactos.to;

import java.util.List;

/**
 * Representa los resultados paginados entregados por las consultas a Areas y Clientes
 * @author Gonzalo Silva
 *
 * @param <T>
 */
public class PaginatedResult<T> {
    private int numeroPagina;
    private int totalPaginas;
    private List<T> data;
    
	public int getNumeroPagina() {
		return numeroPagina;
	}
	public void setNumeroPagina(int numeroPagina) {
		this.numeroPagina = numeroPagina;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
    
    

}