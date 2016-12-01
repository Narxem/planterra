package fr.agez.planterra.data.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import fr.agez.planterra.data.Plant;

/**
 * @author Adrien Agez
 */

public class PlanterraDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "PLANTERRA DATABASE";
    public static final int DATABASE_VERSION = 2;


    protected RuntimeExceptionDao<Plant, Integer> plantDao = null;


    public PlanterraDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Plant.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {

            TableUtils.dropTable(connectionSource, Plant.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(database, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public RuntimeExceptionDao<Plant, Integer> getPlantDao() {
        if (plantDao == null) {
            plantDao = getRuntimeExceptionDao(Plant.class);
        }
        return plantDao;
    }
}
