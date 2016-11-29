package fr.agez.planterra.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.DateTime;

/**
 * @author Adrien Agez
 */

@DatabaseTable(tableName = "Plants", daoClass = PlantDao.class)
public class Plant {

    @DatabaseField(id = true, generatedId = true)
    protected Integer id;
    @DatabaseField(canBeNull = false)
    protected String name;

    protected Plant() {

    }


}
