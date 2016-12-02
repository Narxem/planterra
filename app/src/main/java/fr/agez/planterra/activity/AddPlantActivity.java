package fr.agez.planterra.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import fr.agez.planterra.R;

public class AddPlantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plant);

    }





    protected void addPlant(View v) {
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
                data.putExtra("plant_name", name);
                data.putExtra("daysBtwWatering", daysBtwWatering);
                setResult(RESULT_OK, data);
                finish();
            }
        } catch (NumberFormatException e) {
            error(daysError);
        }
    }

    public void error(String message) {
        TextView errorView = (TextView) findViewById(R.id.add_error);
        errorView.setText(message);
    }
}
