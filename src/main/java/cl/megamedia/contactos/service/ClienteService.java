package cl.megamedia.contactos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cl.megamedia.contactos.to.Cliente;

/**
 * Clase de servicios de base de datos para la tabla Clientes 
 * 
 * @author Gonzalo Silva
 *
 */
@Service
public class ClienteService {

	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	
	 // llamar pl create_cliente 
	 public void crearCliente(Cliente cliente) {
	        String sql = "CALL create_cliente(?, ?, ?, ?, ?)";
	        jdbcTemplate.update(sql, 
	        		cliente.getAreaId(), 
	        		cliente.getNombreCliente(), 
	        		cliente.getFonoContacto(), 
	        		cliente.getEmailContacto(), 
	        		cliente.getUsuario());
	    }
	 
	 // llamar pl  update_cliente
	 public void actualizarCliente(Cliente cliente) {
	        String sql = "CALL update_cliente(?, ?, ?, ?, ?, ?)";
	        jdbcTemplate.update(sql, 
	            cliente.getClienteId(), 
	            cliente.getAreaId(), 
	            cliente.getNombreCliente(), 
	            cliente.getFonoContacto(), 
	            cliente.getEmailContacto(), 
	            cliente.getUsuario()
	        );
	    }
	 
	 // eliminar un cliente
	 public void deleteCliente(int clienteId) {
	        String sql = "CALL delete_cliente(?)";
	        jdbcTemplate.update(sql, clienteId);
	 }
	
}
