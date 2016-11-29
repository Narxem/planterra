package fr.agez.planterra.data.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * @author Adrien Agez
 */

public class PlanterraDatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "PLANTERRA DATABASE";
    public static final int DATABASE_VERSION = 1;


    public PlanterraDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        // CREATE DATABASE
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        // UPGRADE DATABASE
    }
}
