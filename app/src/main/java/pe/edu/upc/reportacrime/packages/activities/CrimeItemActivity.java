package pe.edu.upc.reportacrime.packages.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.R;

/**
 * Created by Andres R on 06/07/2015.
 */
public class CrimeItemActivity extends AppCompatActivity{
    TextView titleTextView;
    TextView descriptionTextView;
    TextView categoryTextView;
    TextView districtTextView;
    TextView statusTextView;
    TextView addressTextView;
    Button backButton;
    Button mapButton;

    double latitude;
    double longitude;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_item);

        Bundle bundle = getIntent().getExtras();
        latitude = bundle.getDouble("latitude");
        longitude = bundle.getDouble("longitude");
        title = bundle.getString("title");

        titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText(bundle.getString("title"));
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText(bundle.getString("description"));
        categoryTextView= (TextView) findViewById(R.id.categoryTextView);
        categoryTextView.setText(bundle.getString("category"));
        districtTextView= (TextView) findViewById(R.id.districtTextView);
        districtTextView.setText(bundle.getString("district"));
        statusTextView= (TextView) findViewById(R.id.statusTextView);
        statusTextView.setText(bundle.getString("status"));
        addressTextView= (TextView) findViewById(R.id.addressTextView);
        addressTextView.setText(bundle.getString("address"));

        mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CrimeItemActivity.this, MapsActivity.class);
                Bundle b = new Bundle();
                double [] latitudes = {latitude};
                b.putDoubleArray("latitude", latitudes);
                double [] longitudes = {longitude};
                b.putDoubleArray("longitude", longitudes);
                ArrayList<String> titles = new ArrayList<String>();
                titles.add(title);
                b.putStringArrayList("name", titles);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
