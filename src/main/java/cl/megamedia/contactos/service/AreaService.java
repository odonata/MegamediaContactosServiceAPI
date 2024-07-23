package cl.megamedia.contactos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import cl.megamedia.contactos.to.Area;

/**
 * Clase de servicios de base de datoas para la tabla Area 
 * 
 * @author Gonzalo Silva
 *
 */
@Service
public class AreaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // Borrar el área
    public void deleteArea(int areaId) {
        String sql = "CALL delete_area(?)";
        jdbcTemplate.update(sql, areaId);
    }
    
    // Crear un área
    public void createArea(Area area) {
        String sql = "CALL create_area(?, ?)";
        jdbcTemplate.update(sql, area.getNombre(), area.getUsuario());
    }
    
    // Llamada a la función get_areas
    public List<Area> getAreas() {
        String sql = "SELECT * FROM get_areas()";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Area.class));
    }
    
    // llamar a la funcion de actualizacion de area: update_area
    public void updateArea(int areaId, String nombreArea, String usuario) {
        jdbcTemplate.update("CALL update_area(?, ?, ?)", areaId, nombreArea, usuario);
    }
}
