package fr.agez.planterra.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;

import fr.agez.planterra.R;
import fr.agez.planterra.data.Plant;
import fr.agez.planterra.data.util.DateFormatter;


/**
 * @author Adrien Agez
 */

public class PlantAdapter extends ArrayAdapter<Plant> {

    DateFormatter formatter = DateFormatter.get();

    public PlantAdapter(Context context, List<Plant> plants) {
        super(context, R.layout.plant_item, plants);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plant plant = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.plant_item, parent, false);


        TextView nameView = (TextView) convertView.findViewById(R.id.plant_name);
        TextView nextWateringView = (TextView)  convertView.findViewById(R.id.plant_next_watering);

        nameView.setText(plant.getName());
        LocalDate nextWatering = plant.getLastWatered().plusDays(plant.getMaxDaysBetweenWatering());
        nextWateringView.setText("Prochain arrosage : " + formatter.format(nextWatering));

        return convertView;
    }

}
