package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import pe.edu.upc.reportacrime.packages.adapters.DistrictsAdapter;
import pe.edu.upc.reportacrime.packages.helpers.UrlHelper;
import pe.edu.upc.reportacrime.packages.models.Crime;
import pe.edu.upc.reportacrime.packages.models.District;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
//LOOKS FOR CRIMES IN A CERTAIN DISTRICT
public class DelictiveZonesActivity extends AppCompatActivity {

    private Spinner districtSpinner;
    private Button buttonMap;
    private ArrayList<District> districts = new ArrayList<>();
    private ArrayList<Crime> crimes = new ArrayList<>();

    private DistrictsAdapter mDistrictsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delictive_zones);

        districts = SplashScreenActivity.getDistricts();

        mDistrictsAdapter = new DistrictsAdapter(this, R.layout.spinner, districts);
        districtSpinner = (Spinner) findViewById(R.id.districtsSpinner);
        districtSpinner.setAdapter(mDistrictsAdapter);

        buttonMap = (Button) findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                District district = (District) districtSpinner.getSelectedItem();
                String searchString = null;
                if (districtSpinner.getSelectedItemId() == 1)
                    searchCrimesDistrict(UrlHelper.CRIMES_URL);
                else {
                    try {
                        searchString = URLEncoder.encode(String.valueOf(district.getId()), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    searchCrimesDistrict(UrlHelper.SEARCH_CRIMES_BY_DISTRICT_URL + searchString);

                }
            }
        });
    }

    public void searchCrimesDistrict(String searchString){
         JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.GET, searchString, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    crimes.clear();
                    JSONArray resultsArray = response.getJSONArray("crimes");
                    int limit = resultsArray.length() > 10 ? 10 : resultsArray.length();

                    for (int pos = 0; pos < limit; pos++){
                        JSONObject result = resultsArray.getJSONObject(pos);
                        String title = result.getString("name");
                        String description = result.getString("description");

                        JSONObject categoryObject = result.getJSONObject("category");
                        String category = categoryObject.getString("name");

                        JSONObject districtObject = result.getJSONObject("district");
                        String district= districtObject.getString("name");

                        JSONObject statusObject = result.getJSONObject("status");
                        String status = statusObject.getString("name");

                        double latitude = result.getDouble("latitude");
                        double longitude = result.getDouble("longitude");
                        String address = result.getString("address");

                        Crime crime = new Crime(title, description, LoginActivity.getUser().getFullName(), district, category, status, latitude, longitude, address);
                        crimes.add(crime);
                    }
                    if( crimes.size() > 0 ){
                        Intent intent = new Intent(DelictiveZonesActivity.this, MapsActivity.class);
                        Bundle b = new Bundle();
                        int size = crimes.size();
                        double[] latitudes = new double[size];
                        double[] longitudes = new double[size];
                        ArrayList<String> titles = new ArrayList<>();
                        for (int pos = 0 ; pos < crimes.size(); pos++ ){
                            latitudes[pos] = crimes.get(pos).getLatitude();
                            longitudes[pos] = crimes.get(pos).getLongitude();
                            titles.add(crimes.get(pos).getName());
                        }
                        b.putDoubleArray("latitude", latitudes);
                        b.putDoubleArray("longitude", longitudes);
                        b.putStringArrayList("name", titles);
                        intent.putExtras(b);
                        startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
