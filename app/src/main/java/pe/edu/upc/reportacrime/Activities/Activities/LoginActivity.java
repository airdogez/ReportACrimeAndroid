package pe.edu.upc.reportacrime.Activities.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbRequest;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;

import pe.edu.upc.reportacrime.Activities.Models.Login;
import pe.edu.upc.reportacrime.Activities.Models.User;
import pe.edu.upc.reportacrime.Activities.Parsers.LoginParser;
import pe.edu.upc.reportacrime.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class LoginActivity extends Activity {
    private Button btnLogin;
    private Button btnRegister;
    private EditText inputEmail;
    private EditText inputPassword;
    private ProgressBar progressBar;
    private static String LOGIN_URL = "http://mobdev-aqws3.c9.io/users/sign_in.json";
    private static User user = null;

    public static User getUser(){
        return user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        inputEmail = (EditText) findViewById(R.id.editTextUser);
        inputPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = inputEmail.getText().toString();
                    String password = inputPassword.getText().toString();
                    try {
                        login(email, password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        );

        progressBar = (ProgressBar)findViewById(R.id.progressBarSignIn);
        progressBar.setVisibility(View.INVISIBLE);

    }

    public void login(String email, String password) throws JSONException {
        String jsonBody = "{\"user\":{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}}";
        JSONObject request = new JSONObject(jsonBody);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
                Request.Method.POST, LOGIN_URL, request, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    System.out.println(response.toString());
                    String name = response.getString("name");
                    String lastname = response.getString("lastname");
                    String email = response.getString("email");
                    String token = response.getString("authentication_token");
                    //TODO: Fix District null Error
                    //int district = response.getInt("district_id");

                    user = new User(name,lastname,email,token,1);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
        }
        );
        Volley.newRequestQueue(this).add(jsonRequest);
    }
}