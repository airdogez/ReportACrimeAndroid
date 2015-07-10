package pe.edu.upc.reportacrime.packages.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

import pe.edu.upc.reportacrime.packages.helpers.SessionManager;
import pe.edu.upc.reportacrime.packages.helpers.UrlHelper;
import pe.edu.upc.reportacrime.packages.models.District;
import pe.edu.upc.reportacrime.packages.models.User;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class LoginActivity extends AppCompatActivity {
    private SessionManager session;
    private Button btnLogin;
    private Button btnRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressDialog progressDialog;
    private static User user = null;

    public static User getUser(){
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        inputEmail = (EditText) findViewById(R.id.editTextUser);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        session = new SessionManager(getApplicationContext());

        if(session.isLoggedIn()){
            user = session.getUser();
            Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
            startActivity(intent);
            finish();
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email = inputEmail.getText().toString();
            String password = inputPassword.getText().toString();
            if(email.trim().length() > 0 && password.trim().length() > 0){
                try {
                    login(email, password);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                    Toast.makeText(getApplicationContext(),
                    "Please enter your login credentials", Toast.LENGTH_LONG).show();
                }
                }
            }
        );

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SplashScreenActivity.getDistricts() != null) {
                    Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    public void login(String email, String password) throws JSONException {
        progressDialog.setMessage("Login in...");
        progressDialog.show();

        //String containing the login information
        String jsonBody = "{\"user\":{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}}";

        JSONObject request = new JSONObject(jsonBody);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, UrlHelper.LOGIN_URL, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                progressDialog.hide();
                try {
                    int id = response.getInt("id");
                    String name = response.getString("name");
                    String lastname = response.getString("lastname");
                    String email = response.getString("email");
                    String token = response.getString("authentication_token");
                    String phone = response.getString("phone");
                    int district_id = response.getInt("district_id");

                    user = new User(id, name, lastname,email,token, district_id, phone);
                    session.setUser(user);

                    Intent intent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                Toast.makeText(getApplicationContext(),"Email/Password did not match, enter again.", Toast.LENGTH_SHORT).show();
                progressDialog.hide();
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
    }
}
