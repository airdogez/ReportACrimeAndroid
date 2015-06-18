package pe.edu.upc.rac_test1.Activities.Activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rac_test1.Activities.Models.Flower;
import pe.edu.upc.rac_test1.Activities.Parsers.FlowerParser;
import pe.edu.upc.rac_test1.R;

/**
 * Created by Miguel on 05/06/2015.
 */
public class History extends ActionBarActivity {


    //TextView output;
    ProgressBar pb;
    List<MyTask> tasks;


    List<Flower> ReportList;
    Button mRequestData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        //root = findViewById(android.R.id.content).getRootView();
        //InitViews.whichClass(this);

        registerEvents();

        mRequestData = (Button)findViewById(R.id.buttonRequestData);
        mRequestData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isOnline()){
                    requestData("http://services.hanselandpetal.com/feeds/flowers.json");
                    //requestData("https://mobdev-aqws3.c9.io/reports.json");
                }   else{
                   // Toast.makeText(this, "Network isn't available", Toast.LENGTH_LONG).show();

                }

            }
        });


        //output = (TextView)findViewById(R.id.tvOutput);
        //output.setMovementMethod(new ScrollingMovementMethod());

        pb = (ProgressBar)findViewById(R.id.progressBarHistory);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();




    }



        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        //return super.onOptionsItemSelected(item);



    private void registerEvents(){

        /// selecciona la lista en pantalla segun su ID
        ListView listHistory = (ListView) findViewById(R.id.listViewHistory);

        // registra una accion para el evento click
        listHistory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();

                // muestra un mensaje
                Toast.makeText(getApplicationContext(), "Haz hecho click en " + itemSeleccionado, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void requestData(String uri) {
        MyTask task = new MyTask();
        task.execute(uri);
    }

    protected void updateDisplay(){

        /*
        if(ReportList != null){
            for(Flower flower : ReportList){
                output.append(flower.getName() + "\n");
            }
        }*/
        List<String> myItems = new ArrayList<>();

        if(ReportList != null){
            int cont= 0;
            for(Flower flower : ReportList){
                myItems.add(flower.getName().toString());
                cont++;
                //output.append(flower.getName() + "\n");
            }
        }
       // String[] myItems = {"Blue","asd","erer"};

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myItems);

        ListView list = (ListView) findViewById(R.id.listViewHistory);

        list.setAdapter(listAdapter);


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
            ReportList = FlowerParser.parseFeed(result);
            updateDisplay();


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
