package pe.edu.upc.reportacrime.Activities.Models;

/**
 * Created by Andres R on 05/07/2015.
 */
public class Crime {
     String name;
     String description;
     String user;
     String district;
     String category;
     String  status;
     double latitude;
     double longitude;
     String address;

     public Crime(String name, String description, String user, String district, String category, String status, double latitude, double longitude, String address) {
          this.name = name;
          this.description = description;
          this.user = user;
          this.district = district;
          this.category = category;
          this.status = status;
          this.latitude = latitude;
          this.longitude = longitude;
          this.address = address;
     }


     public String getName() {
          return name;
     }

     public String getDescription() {
          return description;
     }

     public String getUser() {
          return user;
     }

     public String getDistrict() {
          return district;
     }

     public String getCategory() {
          return category;
     }

     public String getStatus() {
          return status;
     }

     public double getLatitude() {
          return latitude;
     }

     public double getLongitude() {
          return longitude;
     }

     public String getAddress() {
          return address;
     }
}
