package pe.edu.upc.reportacrime.Activities.Models;

/**
 * Created by Miguel on 13/06/2015.
 */
public class Report {
    private int id;
    private String name;
    private String description;
    private int categoryid;
    private int userid;
    private int districtid;
    private String details;
    private int statusid;
    private String url;

    public Report(){



    }

    /*
    public Report(int pId, String pName, String pDescription, int pCategoryid, int pUserid, int pDistrictid , String pDetails, int pStatusid, String pUrl){

        setId(pId);
        setName(pName);
        setDescription(pDescription);
        setCategoryid(pCategoryid);
        setUserid(pUserid);
        setDistrictid(pDistrictid);
        setDetails(pDetails);
        setStatusid(pStatusid);
        setUrl(pUrl);

    }*/

    String getDescription(){
        return description;
    }

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
