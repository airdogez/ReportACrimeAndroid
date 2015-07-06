package pe.edu.upc.reportacrime.Activities.Adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.Activities.Activities.CrimeItemActivity;
import pe.edu.upc.reportacrime.Activities.Models.Crime;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Andres R on 06/07/2015.
 */
public class CrimesAdapter extends RecyclerView.Adapter<CrimesAdapter.ViewHolder>{
    ArrayList<Crime> crimes;

    @Override
    public boolean onFailedToRecycleView(ViewHolder holder) {
        return super.onFailedToRecycleView(holder);
    }

    public CrimesAdapter(ArrayList<Crime> crimes) {
        this.crimes = crimes;
    }

    public CrimesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_crime_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CrimesAdapter.ViewHolder holder, final int position){
        holder.titleTextView.setText(crimes.get(position).getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent crimeItemIntent = new Intent(v.getContext(), CrimeItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("title", crimes.get(position).getName());
                bundle.putString("description", crimes.get(position).getDescription());
                bundle.putString("address", crimes.get(position).getAddress());
                bundle.putString("category", crimes.get(position).getCategory());
                bundle.putString("district", crimes.get(position).getDistrict());
                bundle.putString("status", crimes.get(position).getStatus());
                bundle.putDouble("latitude", crimes.get(position).getLatitude());
                bundle.putDouble("longitude", crimes.get(position).getLongitude());
                crimeItemIntent.putExtras(bundle);
                v.getContext().startActivity(crimeItemIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return crimes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        public ViewHolder(View itemView){
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
        }
    }
}
