package pe.edu.upc.reportacrime.Activities.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.reportacrime.Activities.Models.Flower;

/**
 * Created by Miguel on 14/06/2015.
 */
public class FlowerParser {

    public static List<Flower> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Flower> ResumeList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Flower resume = new Flower();

                resume.setName(obj.getString("name"));
                resume.setCategory(obj.getString("category"));
                resume.setInstructions(obj.getString("instructions"));
                resume.setPrice(obj.getDouble("price"));
                resume.setProductId(obj.getInt("productId"));
                resume.setPhoto(obj.getString("photo"));

                ResumeList.add(resume);

            }
            return ResumeList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
