package pe.edu.upc.reportacrime.packages.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.packages.models.Category;

/**
 * Created by Andres R on 06/07/2015.
 */
public class CategoriesAdapter extends ArrayAdapter<Category> {
    private ArrayList<Category> categories;
    private Context context;

    public CategoriesAdapter(Context context, int resource, ArrayList<Category> objects) {
        super(context, resource, objects);
        this.categories = objects;
        this.context = context;
    }

    public int getCount(){
        return categories.size();
    }

    public Category getItem(int position){
        return categories.get(position);
    }

    public long getItemId(int position){
        return categories.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setText(categories.get(position).getName());
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        TextView label = new TextView(context);
        label.setText(categories.get(position).getName());
        return label;
    }
}
