package fr.agez.planterra.data;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

/**
 * @author Adrien Agez
 */

@DatabaseTable(tableName = "Plants")
public class Plant {

    @DatabaseField(generatedId = true)
    protected int id;
    @DatabaseField(canBeNull = false)
    protected String name;
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    protected LocalDate lastWatered;
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    protected Duration wateringInterval;

    Plant() {}

    public Plant(String name, Duration wateringInterval) {
        this.name = name;
        this.wateringInterval = wateringInterval;
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

    public Duration getWateringInterval() {
        return wateringInterval;
    }

    public void setWateringInterval(Duration wateringInterval) { this.wateringInterval = wateringInterval;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public boolean equals(Object o) {
        if (!(o instanceof Plant))
            return false;
        Plant that = (Plant) o;
        return this.name == that.name;
    }

}
