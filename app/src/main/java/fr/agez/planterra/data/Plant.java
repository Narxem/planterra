package fr.agez.planterra.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.LocalDate;

/**
 * @author Adrien Agez
 */

@DatabaseTable(tableName = "Plants", daoClass = PlantDao.class)
public class Plant {

    @DatabaseField(id = true, generatedId = true)
    protected Integer id;
    @DatabaseField(canBeNull = false)
    protected String name;

    @DatabaseField()
    protected LocalDate lastWatered;

    protected Plant() {

    }

    public Plant(String name) {
        this.name = name;
    }



    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLastWatered() {
        return lastWatered;
    }

    public void setLastWatered(LocalDate lastWatered) {
        this.lastWatered = lastWatered;
    }




}
