package fr.agez.planterra.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fr.agez.planterra.R;
import fr.agez.planterra.data.Plant;



/**
 * @author Adrien Agez
 */

public class PlantAdapter extends ArrayAdapter<Plant> {


    public PlantAdapter(Context context, List<Plant> plants) {
        super(context, R.layout.plant_item, plants);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Plant plant = getItem(position);
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.plant_item, parent, false);


        TextView nameView = (TextView) convertView.findViewById(R.id.plant_name);

        nameView.setText(plant.getName());

        return convertView;
    }

}
