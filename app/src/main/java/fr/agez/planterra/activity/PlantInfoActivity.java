package fr.agez.planterra.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import fr.agez.planterra.R;
import fr.agez.planterra.data.Plant;

public class PlantInfoActivity extends AppCompatActivity {

    Plant plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_info);



        Intent intent = getIntent();
        plant = (Plant) intent.getSerializableExtra("plant");

        TextView nameView = (TextView) findViewById(R.id.edit_name);
        TextView daysView = (TextView) findViewById(R.id.edit_days_btw_watering);

        nameView.setText(plant.getName());
        daysView.setText(String.valueOf(plant.getMaxDaysBetweenWatering()));
    }

    protected void updatePlant(View v) {
        String daysError = "La fréquence d'arrosage représente un nombre de jours. Veuillez entrer un nombre entier supérieur à 1";
        try {
            String name = ((EditText) findViewById(R.id.edit_name)).getText().toString();
            int daysBtwWatering = Integer.parseInt(((EditText) findViewById(R.id.edit_days_btw_watering)).getText().toString());
            // Hint : Btw = Between, not By the way ;)

            if (name.equals(""))
                error("Veuillez entrer un nom pour votre plante");
            else if (!(daysBtwWatering > 1))
                error(daysError);
            else {
                Intent data = getIntent();
                plant.setName(name);
                plant.setMaxDaysBetweenWatering(daysBtwWatering);
                data.putExtra("updatedPlant", plant);
                data.putExtra("delete", false);
                setResult(RESULT_OK, data);
                finish();
            }
        } catch (NumberFormatException e) {
            error(daysError);
        }
    }

    protected void delete(View v) {
        Intent data = getIntent();
        data.putExtra("delete", true);
        setResult(RESULT_OK, data);
        finish();
    }

    public void error(String message) {
        TextView errorView = (TextView) findViewById(R.id.add_error);
        errorView.setText(message);
    }

}
