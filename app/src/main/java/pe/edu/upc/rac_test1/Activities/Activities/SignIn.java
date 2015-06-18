package pe.edu.upc.rac_test1.Activities.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rac_test1.Activities.Models.Login;
import pe.edu.upc.rac_test1.Activities.Parsers.LoginParser;
import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class SignIn extends Activity {
    Button buttonLogin;
    TextView output;
    EditText mETuser;
    ProgressBar pb;
    String user;
    List<MyTask> tasks;

    List<Login> LoginList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        mETuser = (EditText) findViewById(R.id.editTextUser);

        buttonLogin = (Button)findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    user = mETuser.getText().toString();

                    requestData("https://webapi-gmvi-zcrome.c9.io/api/users");
                    //requestData("https://mobdev-aqws3.c9.io/reports.json");
                }   else{
                    // Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();

                }
                //Intent i = new Intent(SignIn.this, MainUser.class);
                //startActivity(i);

            }
        });

        output = (TextView)findViewById(R.id.tvLOGINTEST);
        output.setMovementMethod(new ScrollingMovementMethod());

        pb = (ProgressBar)findViewById(R.id.progressBarSignIn);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();
    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void LoginValidation(){

        boolean Signed = false;
        if(LoginList != null){
            for(Login login : LoginList){
                if(login.getEmail().toString().contentEquals(user)){
                    Intent i = new Intent(SignIn.this, MainUser.class);
                    Signed=true;
                    startActivity(i);

                }
                //output.append(login.getEmail() + "\n");
            }
            if(Signed == false) {
                Toast.makeText(this, "Incorrect Email", Toast.LENGTH_LONG).show();
            }
        }


    }

    protected boolean isOnline(){
        ConnectivityManager cm =  (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();

        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }   else{
            return false;
        }
    }


    private class MyTask extends AsyncTask<String,String,String> {

        @Override
        protected void onPreExecute() {
            // updateDisplay("Starting task");
            // pb.setVisibility(View.VISIBLE);

            if(tasks.size() == 0){
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
            //super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            /*
            for (int i = 0 ; i < params.length; i++){
                publishProgress("Working with " + params[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }*/

            String content = HttpManager.getData(params[0]);
            return content;
            //return "Task Complete";
            //return null;
        }

        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(s);
            LoginList = LoginParser.parseFeed(result);
            LoginValidation();


            tasks.remove(this);
            if(tasks.size() == 0){
                pb.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        protected void onProgressUpdate(String... values) {
            //
            // super.onProgressUpdate(values);
            //updateDisplay(values[0]);
        }
    }



}
