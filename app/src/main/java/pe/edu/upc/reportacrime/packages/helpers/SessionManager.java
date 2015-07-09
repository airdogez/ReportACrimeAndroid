package pe.edu.upc.reportacrime.packages.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import pe.edu.upc.reportacrime.packages.models.User;

/**
 * Created by Andres R on 09/07/2015.
 */
public class SessionManager {

    private static String TAG = SessionManager.class.getSimpleName();
    SharedPreferences sharedPreferences;

    Editor editor;
    Context context;

    int PRIVATE_MODE = 0;

    private static final String PREF_NAME = "ReportacrimeLogin";

    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";


    public SessionManager(Context context){
        this.context = context;

        sharedPreferences =context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);

        editor = sharedPreferences.edit();
    }

    public void setLogin(boolean isLoggedIn, User user){
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.putString("name", user.getName());
        editor.putString("lastname", user.getLastname());
        editor.putString("email", user.getEmail());
        editor.putString("token", user.getToken());
        editor.putInt("id", user.getId());
        editor.putInt("district", user.getDistrict());

        editor.commit();

        Log.d(TAG, "User login session modified");
    }

    public User getUser(){
        String name = sharedPreferences.getString("name", null);
        String lastname = sharedPreferences.getString("lastname", null);
        String email = sharedPreferences.getString("email", null);
        String token = sharedPreferences.getString("token", null);
        int id = sharedPreferences.getInt("id", -1);
        int district = sharedPreferences.getInt("district", -1);
        User user = new User(id,name,lastname,email,token,district);
        return user;
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(KEY_IS_LOGGEDIN, false);
    }
}
