package pe.edu.upc.rac_test1.Activities.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.edu.upc.rac_test1.Activities.Models.Report;

/**
 * Created by Miguel on 13/06/2015.
 */
public class ReportParser {

    public static List<Report> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Report> ResumeList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Report resume = new Report();

                resume.setId(obj.getInt("id"));
                resume.setName(obj.getString("name"));
                resume.setDescription(obj.getString("Description"));
                resume.setCategoryid(obj.getInt("category_id"));
                resume.setUserid(obj.getInt("user_id"));
                resume.setDistrictid(obj.getInt("district_id"));
                resume.setDetails(obj.getString("details"));
                resume.setStatusid(obj.getInt("status_id"));
                resume.setUrl(obj.getString("url"));

                ResumeList.add(resume);

            }
            return ResumeList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
