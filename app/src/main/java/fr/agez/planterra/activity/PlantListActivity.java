package fr.agez.planterra.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fr.agez.planterra.R;
import fr.agez.planterra.activity.adapter.PlantAdapter;
import fr.agez.planterra.data.Plant;

/**
 * @author Adrien Agez
 */
public class PlantListActivity extends AppCompatActivity {

    protected ListView listView;
    protected ListAdapter adapter;

    public static List<Plant> TEST;

    static {
        TEST = new ArrayList<>();
        TEST.add(new Plant("1ere plante"));
        TEST.add(new Plant("2eme plante"));
        TEST.add(new Plant("3eme plante"));
        TEST.add(new Plant("4eme plante"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        listView = (ListView) findViewById(R.id.plant_list);
        listView.setAdapter(new PlantAdapter(this, TEST));

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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
