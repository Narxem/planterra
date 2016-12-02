package fr.agez.planterra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.table.TableUtils;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

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

    protected List<Plant> plants;

    private static final int ADD_PLANT_REQUEST = 1;
    private static final int UPDATE_PLANT_REQUEST = 2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dao = getHelper().getPlantDao();
        plants = dao.queryForAll();
        listView = (ListView) findViewById(R.id.plant_list);
        updateView();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Object o = listView.getItemAtPosition(position);
                Plant plant = (Plant) o;
                Intent intent = new Intent(PlantListActivity.this, PlantInfoActivity.class);
                intent.putExtra("plant", plant);
                startActivityForResult(intent, UPDATE_PLANT_REQUEST);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int index, long id) {

                Plant plant = (Plant) listView.getItemAtPosition(index);
                plant.setLastWatered(LocalDate.now());
                dao.update(plant);
                updateView();
                return true;
            }
        });

    }

    protected void updateView() {
        plants = dao.queryForAll();
        if (plants.isEmpty()) {
            listView.setVisibility(View.INVISIBLE);
            findViewById(R.id.populate).setVisibility(View.VISIBLE);
            findViewById(R.id.create_plant2).setVisibility(View.VISIBLE);
        }else {
            findViewById(R.id.populate).setVisibility(View.INVISIBLE);
            findViewById(R.id.create_plant2).setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            listView.setAdapter(new PlantAdapter(this, plants));
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
        if (item.getItemId() == R.id.create_plant)
            toAddPlant(new View(this)); // THIS IS A FAKE ARGUMENT, BECAUSE toAddPlant METHOD NEEDS A VIEW ARGUMENT, BUT WE NEVER USE IT


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

        Plant plant = new Plant("Pélargonium", 4);
        plant.setLastWatered(LocalDate.now().minusDays(3));

        dao.create(plant);
        dao.create(new Plant("Geranium", 5));
        dao.create(new Plant("Primevère", 3));
        dao.create(new Plant("Bégonia", 3));
        dao.create(new Plant("Passiflore", 5));
        dao.create(new Plant("Muguet", 4));
        dao.create(new Plant("Mandragore", 2));
        dao.create(new Plant("Branchiflore", 6));
        plant = new Plant("Bubobulb", 2);
        plant.setLastWatered(new LocalDate(2015, 12, 24));
        dao.create(plant);
        dao.create(new Plant("Mimbulus Mimbletonia", 8));



        updateView();
    }

    protected void toAddPlant(View v) {
        // WE DON'T NEED THE View ARGUMENT.
        // WE CAN'T USE IT, BECAUSE onOptionSelected METHOD SEND A FAKE ARGUMENT
        Intent intent = new Intent(this, AddPlantActivity.class);
        startActivityForResult(intent, ADD_PLANT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_PLANT_REQUEST && resultCode == RESULT_OK) {
            String name = data.getStringExtra("plant_name");
            int daysBtwWatering = data.getIntExtra("daysBtwWatering", 1);
            Plant plant = new Plant(name, daysBtwWatering);
            dao.create(plant);
            updateView();
        }
        if (requestCode == UPDATE_PLANT_REQUEST && resultCode == RESULT_OK) {
            boolean delete = (boolean) data.getBooleanExtra("delete", false);
            if (!delete) {
                Plant plant = (Plant) data.getSerializableExtra("updatedPlant");
                dao.update(plant);
                updateView();
            }
            else {
                Plant plant = (Plant) data.getSerializableExtra("plant");
                dao.delete(plant);
                updateView();
            }
        }
    }

}