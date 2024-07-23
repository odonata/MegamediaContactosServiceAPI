package cl.megamedia.contactos.swaggerapi.controllers;

import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import cl.megamedia.contactos.constantes.Constantes;
import cl.megamedia.contactos.service.AreaService;
import cl.megamedia.contactos.service.PaginacionService;
import cl.megamedia.contactos.to.Area;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class MegaMediaContactosController {
	
	private static final Logger logger = LoggerFactory.getLogger(MegaMediaContactosController.class);
	
	@Autowired
	private PaginacionService paginacionService;
	
	@Autowired
	private  AreaService areaService;
	
	
	
	@GetMapping("/areas")
    public ResponseEntity<?> getAreasPaginadas(
            @RequestParam(value = "pagina", defaultValue = "1") int numeroPagina,
            @RequestParam(value = "registrosPorPagina", defaultValue = "10") int registrosPorPagina,
            @RequestParam(value = "busqueda", defaultValue = "") String busqueda) throws Exception {
        return ResponseEntity.ok(paginacionService.getAreasPaginadas(numeroPagina, registrosPorPagina, busqueda));
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> getClientesPaginados(
            @RequestParam(value = "pagina", defaultValue = "1") int numeroPagina,
            @RequestParam(value = "registrosPorPagina", defaultValue = "10") int registrosPorPagina,
            @RequestParam(value = "busqueda", defaultValue = "") String busqueda) throws Exception {
        return ResponseEntity.ok(paginacionService.getClientesPaginados(numeroPagina, registrosPorPagina, busqueda));
    }

    
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
                : "Error desconocido";
            response.put("codigoError", Constantes.CODIGO_INTEGRIDAD);
            response.put("mensaje", mensaje);
            return ResponseEntity.ok(response);
        }
    }
	
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
            logger.error(e.getMessage());
            Map<String, String> response = new HashMap<>();
            response.put("codigoError", Constantes.CODIGO_ERROR_GENERAL);
            response.put("mensaje", "Error al crear el área");
            return ResponseEntity.ok(response);
        }
    }
    
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
	
	public static boolean searchInStackTrace(Throwable throwable, String searchString) {
		boolean resultado = false;
		String stacktractStr = getStackTrace(throwable);
		boolean encontrado = stacktractStr.contains(searchString);
		if (encontrado) resultado = true;
		logger.info("searchInStackTrace timeout:"+resultado);
        return resultado;
    }
	
	public static String getStackTrace(Throwable throwable) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        throwable.printStackTrace(printWriter);
        return stringWriter.toString();
    }

}
