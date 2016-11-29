package fr.agez.planterra.data;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

/**
 * @author Adrien Agez
 */

public class PlantDao extends BaseDaoImpl<Plant, Integer> {


    protected PlantDao(ConnectionSource connectionSource, Class<Plant> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }
}
