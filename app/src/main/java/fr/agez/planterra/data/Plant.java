package fr.agez.planterra.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * @author Adrien Agez
 */

@DatabaseTable(tableName = "Plants")
public class Plant implements Serializable {

    @DatabaseField(generatedId = true)
    protected int id;
    @DatabaseField(canBeNull = false)
    protected String name;
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    protected LocalDate lastWatered;
    @DatabaseField
    protected int maxDaysBetweenWatering;

    Plant() {}

    public Plant(String name, int maxDaysBetweenWatering) {
        this.name = name;
        this.maxDaysBetweenWatering = maxDaysBetweenWatering;
        this.lastWatered = LocalDate.now();
    }

    public String getName() {
        return name;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMaxDaysBetweenWatering() {
        return maxDaysBetweenWatering;
    }

    public void setMaxDaysBetweenWatering(int maxDaysBetweenWatering) {
        this.maxDaysBetweenWatering = maxDaysBetweenWatering;
    }


}
