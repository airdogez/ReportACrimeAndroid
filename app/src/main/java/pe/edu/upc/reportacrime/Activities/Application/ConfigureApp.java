package pe.edu.upc.reportacrime.Activities.Application;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Miguel on 13/06/2015.
 */
public class ConfigureApp extends Application {

    public static final String TAG = ConfigureApp.class
            .getSimpleName();

    private RequestQueue mRequestQueue;

    private static ConfigureApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized ConfigureApp getInstance() { return mInstance;}

    public RequestQueue getRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue= Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue (Request<T> req, String tag) {

        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void AddToRequestQueue (Request <T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void CancelPendingRequests(Object tag) {
        if(mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
