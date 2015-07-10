package pe.edu.upc.reportacrime.packages.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.packages.adapters.CrimesAdapter;
import pe.edu.upc.reportacrime.packages.models.Crime;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class CrimeHistoryActivity extends AppCompatActivity {

    ArrayList<Crime> crimes;

    private RecyclerView mCrimesRecyclerView;
    private RecyclerView.Adapter mCrimesAdapter;
    private RecyclerView.LayoutManager mCrimesLayoutManager;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_history);
        mCrimesRecyclerView = (RecyclerView)findViewById(R.id.crimesHistoryRecycler);
        mCrimesRecyclerView.setHasFixedSize(true);
        mCrimesLayoutManager = new LinearLayoutManager(this);
        mCrimesRecyclerView.setLayoutManager(mCrimesLayoutManager);
        crimes = new ArrayList<>();
        crimes.addAll(MainMenuActivity.getCrimes());
        mCrimesAdapter = new CrimesAdapter(crimes);
        mCrimesRecyclerView.setAdapter(mCrimesAdapter);
        btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void clearData(){
        crimes.removeAll(crimes);
    }

    public void initializeData(){
        crimes = new ArrayList<>();
    }

}
