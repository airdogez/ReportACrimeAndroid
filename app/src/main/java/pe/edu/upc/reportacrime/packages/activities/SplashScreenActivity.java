package pe.edu.upc.reportacrime.packages.activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

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
import pe.edu.upc.reportacrime.packages.helpers.UrlHelper;
import pe.edu.upc.reportacrime.packages.models.Category;
import pe.edu.upc.reportacrime.packages.models.District;

/**
 * Created by Miguel on 04/06/2015.
 */
public class SplashScreenActivity extends Activity{


    private static ArrayList<District> districts= new ArrayList<>();
    private static ArrayList<Category> categories= new ArrayList<>();
    public static ArrayList<District> getDistricts(){
        return districts;
    }
    public static ArrayList<Category> getCategories(){
        return categories;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    searchCategories();
                    searchDistricts();
                    Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        timerThread.start();
    }

    private void searchDistricts() {
        JsonObjectRequest districtsRequest = new JsonObjectRequest(
                Request.Method.GET, UrlHelper.DISTRICTS_URL, null, new Response.Listener<JSONObject>() {
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

    private void searchCategories() {
        JsonObjectRequest categoriesRequest = new JsonObjectRequest(
                Request.Method.GET, UrlHelper.CATEGORIES_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    categories.clear();
                    JSONArray resultsArray = response.getJSONArray("categories");
                    for(int pos = 0; pos < resultsArray.length(); pos++){
                        JSONObject result = resultsArray.getJSONObject(pos);
                        int id = result.getInt("id");
                        String name = result.getString("name");
                        String description = result.getString("description");
                        Category category = new Category(id, name,description);
                        categories.add(category);
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
        Volley.newRequestQueue(this).add(categoriesRequest);
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
