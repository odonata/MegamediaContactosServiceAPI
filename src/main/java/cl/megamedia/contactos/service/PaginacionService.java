package cl.megamedia.contactos.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.megamedia.contactos.to.Area;
import cl.megamedia.contactos.to.Cliente;
import cl.megamedia.contactos.to.PaginatedResult;

import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Esta clase de provee metodos para llamar a las funciomnes: 
 * 
 * Funcion get_areas_paginadas   :  que entrega la consulta de areas de forma paginada
 * Funcion get_clientes_paginados:  que entrega la consulta de clientes de forma paginada
 *
 * @author Gonzalo Silva
 *
 */
@Service
public class PaginacionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    // llamar a función get_areas_paginadas (pagina, numero de registros por pagina , campo de busqueda )
    // el campo de búsqueda puede ser vacio o contener una cadena, la busqueda se hace sobre el campo nombre_area
    public PaginatedResult<Area> getAreasPaginadas(int numeroPagina, int registrosPorPagina, String busqueda) throws Exception {
        String sql = "SELECT * FROM get_areas_paginadas(?, ?, ?)";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, numeroPagina, registrosPorPagina, busqueda);

        return mapToPaginatedResult(result, Area.class);
    }

    // llamar a función get_clientes_paginados (pagina, numero de registros por pagina , campo de busqueda )
    // el campo de búsqueda puede ser vacio o contener una cadena, la busqueda se hace sobre el campo nombre_cliente

    public PaginatedResult<Cliente> getClientesPaginados(int numeroPagina, int registrosPorPagina, String busqueda) throws Exception {
        String sql = "SELECT * FROM get_clientes_paginados(?, ?, ?)";
        Map<String, Object> result = jdbcTemplate.queryForMap(sql, numeroPagina, registrosPorPagina, busqueda);

        return mapToPaginatedResult(result, Cliente.class);
    }

    // entregar los resultados paginados
    private <T> PaginatedResult<T> mapToPaginatedResult(Map<String, Object> result, Class<T> clazz) throws Exception {
        PaginatedResult<T> paginatedResult = new PaginatedResult<>();

        paginatedResult.setNumeroPagina((Integer) result.get("numero_pagina"));
        paginatedResult.setTotalPaginas((Integer) result.get("total_paginas"));

        Object dataObject = result.get("data");
        List<T> data;

        if (dataObject instanceof PGobject) {
            PGobject pgObject = (PGobject) dataObject;
            String json = pgObject.getValue(); // Obtener el valor JSON del PGobject
            
            // Parsear el JSON
            JsonNode jsonNode = objectMapper.readTree(json);
            data = objectMapper.convertValue(jsonNode, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } else {
            throw new IllegalArgumentException("Unexpected type for data: " + dataObject.getClass().getName());
        }

        paginatedResult.setData(data);

        return paginatedResult;
    }
}