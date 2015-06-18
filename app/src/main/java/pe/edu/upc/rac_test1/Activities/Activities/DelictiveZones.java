package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class DelictiveZones extends Activity {

    Button buttonMap;
    String district;
    Spinner sp;

    public static final String EXTRA_ADDRESS = "com.coderefer.switchactivityandroid.address";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delictive_zones);

        buttonMap = (Button)findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent searchAddress = new  Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + district));
                //startActivity(searchAddress);

                Intent i = new Intent(DelictiveZones.this, MapsActivity.class);

                i.putExtra(EXTRA_ADDRESS, district);               

                startActivity(i);
            }
        });


        sp = (Spinner)findViewById(R.id.spinnerDistricts);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(
                this, R.array.DistrictsNames, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        sp.setAdapter(adapter);

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                district = parentView.getItemAtPosition(position).toString();
                Toast.makeText(parentView.getContext(), "Haz Seleccionado " + parentView.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}
