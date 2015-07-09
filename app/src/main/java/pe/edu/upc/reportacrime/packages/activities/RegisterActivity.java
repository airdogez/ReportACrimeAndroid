package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pe.edu.upc.reportacrime.R;
import pe.edu.upc.reportacrime.packages.adapters.DistrictsAdapter;
import pe.edu.upc.reportacrime.packages.models.District;

/**
 * Created by Miguel on 05/06/2015.
 */
public class RegisterActivity extends Activity {

    private Button btnRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private EditText inputName;
    private EditText inputLastname;
    private EditText inputPhone;

    private Spinner spinnerDistrict;
    private DistrictsAdapter mDistrictAdapter;
    private ArrayList<District> districts;

    private static String REGISTER_URL = "http://mobdev-aqws3.c9.io/users";
    private static String DISTRICTS_URL = "http://mobdev-aqws3.c9.io/api/v1/districts";
    private Button btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        searchDistricts();

        mDistrictAdapter = new DistrictsAdapter(this, R.layout.spinner, districts);
        spinnerDistrict = (Spinner)findViewById(R.id.districtsSpinner);
        spinnerDistrict.setAdapter(mDistrictAdapter);

        inputEmail = (EditText)findViewById(R.id.editTextEmail);
        inputPassword= (EditText)findViewById(R.id.editTextPassword);
        inputName = (EditText)findViewById(R.id.editTextName);
        inputLastname = (EditText)findViewById(R.id.editTextLastName);
        inputPhone = (EditText)findViewById(R.id.editTextPhone);

        btnRegister = (Button)findViewById(R.id.buttonRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Verify that every EditText is Filled.
                if( inputEmail.getText().toString().length() <= 0){
                }
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String name = inputName.getText().toString();
                String lastname = inputLastname.getText().toString();
                //TODO: Add Phone
                try {
                    registerUser(email, password, name, lastname);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                //Intent i = new Intent(RegisterActivity.this, .class);
                // startActivity(i);
            }
        });

        btnBack = (Button)findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registerUser(String email, String password, String name, String lastname) throws JSONException {
        String jsonBody = "{\"user\":{\"email\":\"" + email
                            + "\",\"name\":\"" + name
                            + "\",\"lastname\":\"" + lastname
                            + "\",\"district_id\":\"" + 1
                            + "\",\"password\":\"" + password
                            + "\",\"password_confirmation\":\"" + password + "\"}}";
        JSONObject request = new JSONObject(jsonBody);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, REGISTER_URL, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(RegisterActivity.this, "Registration succesful",Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
        finish();
    }

     private void searchDistricts() {
        JsonObjectRequest districtsRequest = new JsonObjectRequest(
                Request.Method.GET, DISTRICTS_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    districts.clear();
                    JSONArray resultsArray = response.getJSONArray("districts");
                    for(int pos = 0; pos < resultsArray.length(); pos++){
                        JSONObject result = resultsArray.getJSONObject(pos);
                        int id = result.getInt("id");
                        String name = result.getString("name");
                        District district = new District(id, name);
                        districts.add(district);
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
        Volley.newRequestQueue(this).add(districtsRequest);
    }
}
