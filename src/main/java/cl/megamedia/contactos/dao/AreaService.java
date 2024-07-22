package cl.megamedia.contactos.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Esta clase permite elimimnar un área de negocio
 * 
 * @author Gonzalo Silva
 *
 */
@Service
public class AreaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    // borrar el área
    public void deleteArea(int areaId) {
        String sql = "SELECT delete_area(?)";
        jdbcTemplate.update(sql, areaId);
    }
}
