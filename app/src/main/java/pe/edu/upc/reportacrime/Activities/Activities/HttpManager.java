package pe.edu.upc.reportacrime.Activities.Activities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Miguel on 13/06/2015.
 */
public class HttpManager {

    /*
    public static String getData(String uri){
        AndroidHttpClient client = AndroidHttpClient.newInstance("AndroidAgent");
        HttpGet request = new HttpGet(uri);
        HttpResponse response;

        try{
            response = client.execute(request);
            return EntityUtils.toString(response.getEntity());
        }   catch(Exception e){
            e.printStackTrace();
            return null;
        }   finally{
            client.close();
        }

    }*/

    public static String getData(String uri){

        BufferedReader reader = null;
        try{
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while((line = reader.readLine()) != null){
                sb.append(line + "\n");
            }

            return sb.toString();
        }   catch (Exception e){
            e.printStackTrace();
            return null;
        }   finally {
            if(reader !=  null) {
                try{
                    reader.close();
                }   catch (IOException e){
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }
}