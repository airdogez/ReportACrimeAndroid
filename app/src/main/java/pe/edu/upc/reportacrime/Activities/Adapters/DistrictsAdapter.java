package pe.edu.upc.reportacrime.Activities.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.Activities.Models.District;

/**
 * Created by Andres R on 06/07/2015.
 */
public class DistrictsAdapter  extends ArrayAdapter<District>{
    private ArrayList<District> districts;
    private Context context;

    public DistrictsAdapter(Context context, int resource, ArrayList<District> objects) {
        super(context, resource, objects);
        this.districts = objects;
        this.context= context;
    }

    public int getCount(){
        return districts.size();
    }

    public District getItem(int position){
        return districts.get(position);
    }

    public long getItemId(int position){
        return districts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setText(districts.get(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setText(districts.get(position).getName());
        return label;
    }
}
