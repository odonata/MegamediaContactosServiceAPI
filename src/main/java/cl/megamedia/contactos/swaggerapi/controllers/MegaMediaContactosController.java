package cl.megamedia.contactos.swaggerapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cl.megamedia.contactos.constantes.Constantes;
import cl.megamedia.contactos.service.AreaService;
import cl.megamedia.contactos.service.ClienteService;
import cl.megamedia.contactos.service.PaginacionService;
import cl.megamedia.contactos.service.TiempoService;
import cl.megamedia.contactos.to.Area;
import cl.megamedia.contactos.to.Cliente;
import cl.megamedia.contactos.to.EstadoDelClimaResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controlador de endpoint rest
 * @author Gonzalo Silva P.
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:8000")
public class MegaMediaContactosController {
	
	private static final Logger logger = LoggerFactory.getLogger(MegaMediaContactosController.class);
	
	@Autowired
	private PaginacionService paginacionService;
	
	@Autowired
	private  AreaService areaService;
	
	@Autowired
    private ClienteService clienteService;
	
    @Autowired
    private TiempoService tiempoService;
	
	/**
	 * Obtiene todas las areas de forma paginada
	 * @param numeroPagina		 : número de pagina buscada
	 * @param registrosPorPagina : cuantos registros se deben devolver 
	 * 							   por cada pagina
	 * @param busqueda : este campo permite hacer búsquedas sobre el 
	 * 					 nombre de área de negocio y busca dentro del 
	 * 					 texto de nombre de área.
	 * 					 PL relacionado : get_areas_paginadas
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/areas")
    public ResponseEntity<?> getAreasPaginadas(
            @RequestParam(value = "pagina", defaultValue = "1") int numeroPagina,
            @RequestParam(value = "registrosPorPagina", defaultValue = "10") int registrosPorPagina,
            @RequestParam(value = "busqueda", defaultValue = "") String busqueda,
            @RequestParam(value = "filtroBusqueda", defaultValue = "") String filtroBusqueda,
            @RequestParam(value = "tipoBusqueda", defaultValue = "") String tipoBusqueda) throws Exception {
		
		// si tipo busqueda es distinto de normal, significa que se busca para modificar, por lo tanto
		// se envia al pl para buscar el area_id y entregar solo la información del registrio 
		// que se va a modificar
		if(!tipoBusqueda.equalsIgnoreCase("NORMAL")) {
			
			busqueda = filtroBusqueda;
		}
		
        return ResponseEntity.ok(paginacionService.getAreasPaginadas(numeroPagina, registrosPorPagina, busqueda, tipoBusqueda));
    }

	/**
	 * Obtiene todas las clientes de forma paginada
	 * @param numeroPagina		 : número de pagina buscada
	 * @param registrosPorPagina : cuantos registros se deben devolver 
	 * 							   por cada pagina
	 * @param busqueda : este campo permite hacer búsquedas sobre:
	 * 					 nombre area , nombre cliente , fono , email
	 * 					 La busqueda se hace buscando coincidencias dentro de esos
	 * 					 campos en un OR pàra cada campo
	 * 					 PL relacionado : get_clientes_paginados
	 * @return
	 * @throws Exception
	 */
    @GetMapping("/clientes")
    public ResponseEntity<?> getClientesPaginados(
            @RequestParam(value = "pagina", defaultValue = "1") int numeroPagina,
            @RequestParam(value = "registrosPorPagina", defaultValue = "10") int registrosPorPagina,
            @RequestParam(value = "busqueda", defaultValue = "") String busqueda,
            @RequestParam(value = "filtroBusqueda", defaultValue = "") String filtroBusqueda,
            @RequestParam(value = "tipoBusqueda", defaultValue = "") String tipoBusqueda
            ) throws Exception {
    	
    		// si tipo busqueda es distinto de normal, significa que se busca para modificar, por lo tanto
    		// se envia al pl para buscar el cliente_id y entregar solo la información del registrio 
    		// que se va a modificar
    		if(!tipoBusqueda.equalsIgnoreCase("NORMAL")) {
    			
    			busqueda = filtroBusqueda;
    		}
        return ResponseEntity.ok(paginacionService.getClientesPaginados(numeroPagina, registrosPorPagina, busqueda, tipoBusqueda));
    }

    /**
     * Borrar Area de Negocio 
     * PL relacionado : delete_area
     * 
     * @param areaId
     * @return
     */
    @DeleteMapping("/areas/{id}")
    public ResponseEntity<Map<String, String>> deleteArea(@PathVariable("id") int areaId) {
        try {
            areaService.deleteArea(areaId);
            // Respuesta exitosa
            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Manejo de error de integridad referencial
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     * Actualizar Area de negocios
     * @param areaId
     * @param nombreArea
     * @param usuario
     * @return
     */
    @PutMapping("/areaActualizar/{area_id}/{nombre_area}/{usuario}")
    public ResponseEntity<Map<String, String>> updateArea(
            @PathVariable("area_id") int areaId,
            @PathVariable("nombre_area") String nombreArea,
            @PathVariable("usuario") String usuario) {

        try {
            areaService.updateArea(areaId, nombreArea, usuario);
            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
     }

	
    
    /**
     * Crear un Area de Ngocio
     * PL relacionado : create_area
     * 
     * @param nombreArea
     * @param usuario
     * @return
     */
    @PostMapping("/areas")
    public ResponseEntity<Map<String, String>> createArea(
            @RequestParam("nombre") String nombreArea,
            @RequestParam("usuario") String usuario) {
        try {
            Area area = new Area();
            area.setNombre(nombreArea);
            area.setUsuario(usuario);

            areaService.createArea(area);

            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	// Manejo de error de integridad referencial
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     * Obtener las areas de negocio 
     * PL relacionado : get_areas
     * 
     * @return
     */
    @GetMapping("/areasList")
    public ResponseEntity<List<Area>> getAreas() {
        try {
            List<Area> areas = areaService.getAreas();
            return ResponseEntity.ok(areas);
        } catch (Exception e) {
            // Manejo de errores
            return ResponseEntity.status(500).build();
        }
    }
    
    /**
     * Crear Datos de cliente
     * PL relacionado : create_cliente
     * 
     * @param idArea
     * @param nombreCliente
     * @param fonoContacto
     * @param emailContacto
     * @param usuario
     * @return
     */
    @PostMapping("/clienteCrear")
    public ResponseEntity<Map<String, String>>  createCliente(
            @RequestParam("area_id") String idArea,
            @RequestParam("nombre_cliente") String nombreCliente,
    		@RequestParam("fono_contacto") String fonoContacto,
    		@RequestParam("email_contacto") String emailContacto,
    		@RequestParam("usuario") String usuario){
        try {
        	Cliente cliente = new Cliente();
        	
        	cliente.setAreaId(Integer.parseInt(idArea));
        	cliente.setEmailContacto(emailContacto);
        	cliente.setFonoContacto(fonoContacto);
        	cliente.setNombreCliente(nombreCliente);
        	cliente.setUsuario(usuario);
        	
            clienteService.crearCliente(cliente);
            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     * Actualizar datos del cliente
     * PL relacionado : update_cliente
     * 
     * @param clienteId
     * @param areaId
     * @param nombreCliente
     * @param fonoContacto
     * @param emailContacto
     * @param usuario
     * @return
     */
    @PutMapping("/clienteActualizar/{cliente_id}/{area_id}/{nombre_cliente}/{fono_contacto}/{email_contacto}/{usuario}")
    public ResponseEntity<Map<String, String>>  updateCliente(
            @PathVariable("cliente_id") int clienteId,
            @PathVariable("area_id") int areaId,
            @PathVariable("nombre_cliente") String nombreCliente,
            @PathVariable("fono_contacto") String fonoContacto,
            @PathVariable("email_contacto") String emailContacto,
            @PathVariable("usuario") String usuario) {

        try {
            Cliente cliente = new Cliente();
            cliente.setClienteId(clienteId);
            cliente.setAreaId(areaId);
            cliente.setNombreCliente(nombreCliente);
            cliente.setFonoContacto(fonoContacto);
            cliente.setEmailContacto(emailContacto);
            cliente.setUsuario(usuario);

            clienteService.actualizarCliente(cliente);

            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
    
    /**
     * Borrar un cliente
     * PL relacionado : delete_cliente
     * 
     * @param clienteId
     * @return
     */
    @DeleteMapping("/deleteCliente/{cliente_id}")
    public ResponseEntity<Map<String, String>>  deleteCliente(@PathVariable("cliente_id") int clienteId) {
        try {
            clienteService.deleteCliente(clienteId);
            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_EXITO);
            response.put("mensaje", Constantes.MENSAJE_EXITO);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
        	logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            String mensaje = e.getMessage().contains("constraint") 
                ? Constantes.MENSAJE_ERROR_INTEGRIDAD 
                : e.getMessage();
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
	
    

    /**
     * Obtener json del estado del clima
     * @param ciudad
     * @return
     */
    @CrossOrigin(origins = "*")
    @GetMapping("/estadoDelClima")
    public ResponseEntity<EstadoDelClimaResponse> getGraficoTiempo(@RequestParam("ciudad") String ciudad) {
        EstadoDelClimaResponse response = tiempoService.generarGrafico(ciudad);
        if (response != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

            return new ResponseEntity<>(response, headers, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    /**
     * Buscar dentro del stack trace , solo durante desarrollo
     * @param throwable
     * @param searchString
     * @return
     */
	public static boolean searchInStackTrace(Throwable throwable, String searchString) {
		boolean resultado = false;
		String stacktractStr = getStackTrace(throwable);
		boolean encontrado = stacktractStr.contains(searchString);
		if (encontrado) resultado = true;
		logger.info("searchInStackTrace timeout:"+resultado);
        return resultado;
    }
	
	/**
	 * Convertir de stack trace a string
	 * @param throwable
	 * @return
	 */
	public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
