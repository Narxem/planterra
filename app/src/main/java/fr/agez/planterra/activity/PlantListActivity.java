package fr.agez.planterra.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;

import org.joda.time.Duration;

import java.util.ArrayList;
import java.util.List;

import fr.agez.planterra.R;
import fr.agez.planterra.activity.adapter.PlantAdapter;
import fr.agez.planterra.data.Plant;
import fr.agez.planterra.data.util.PlanterraDatabaseHelper;

/**
 * @author Adrien Agez
 */
public class PlantListActivity extends AppCompatActivity {

    protected ListView listView;
    protected PlanterraDatabaseHelper helper;
    protected RuntimeExceptionDao<Plant, Integer> dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = getHelper().getPlantDao();

        List <Plant> plants = dao.queryForAll();

        listView = (ListView) findViewById(R.id.plant_list);
        if (!plants.isEmpty()) {
            listView.setAdapter(new PlantAdapter(this, plants));
        }
        else {
            findViewById(R.id.populate).setVisibility(View.VISIBLE);
            findViewById(R.id.create_plant2).setVisibility(View.VISIBLE);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_plant_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    private PlanterraDatabaseHelper getHelper() {
        if (helper == null) {
            helper = OpenHelperManager.getHelper(this, PlanterraDatabaseHelper.class);
        }
        return helper;
    }

    protected void populateDatabase(View v) {

        getHelper().getPlantDao();

        dao.create(new Plant("Pelargonium", 1));
        dao.create(new Plant("Geranium", 5));
        dao.create(new Plant("Primevère", 3));
        dao.create(new Plant("Bégonia", 3));
        dao.create(new Plant("Passiflore", 5));
        dao.create(new Plant("Muguet", 4));
        dao.create(new Plant("Mandragore", 1));
        dao.create(new Plant("Branchiflore", 6));
        dao.create(new Plant("Bubobulb", 2));
        dao.create(new Plant("Mimbulus Mimbletonia", 8));


        listView.setAdapter(new PlantAdapter(this, dao.queryForAll()));
        v.setVisibility(View.INVISIBLE);
        findViewById(R.id.create_plant2).setVisibility(View.INVISIBLE);
    }

}
