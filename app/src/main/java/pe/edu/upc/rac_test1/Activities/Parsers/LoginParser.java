package pe.edu.upc.rac_test1.Activities.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rac_test1.Activities.Models.Login;

/**
 * Created by Miguel on 15/06/2015.
 */
public class LoginParser {
    public static List<Login> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Login> ResumeList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Login resume = new Login();

                resume.setId(obj.getInt("id"));
                resume.setEmail(obj.getString("email"));

                ResumeList.add(resume);

            }
            return ResumeList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
