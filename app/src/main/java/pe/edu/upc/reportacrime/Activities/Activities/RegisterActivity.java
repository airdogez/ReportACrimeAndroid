package pe.edu.upc.reportacrime.Activities.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import pe.edu.upc.reportacrime.R;

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
    private static String REGISTER_URL = "http://mobdev-aqws3.c9.io/users";
    private Button btnBack;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
}
