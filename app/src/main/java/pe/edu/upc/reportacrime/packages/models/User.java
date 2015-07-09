package pe.edu.upc.reportacrime.packages.models;

/**
 * Created by Andres R on 05/07/2015.
 */
public class User {
    int id;
    String name;
    String lastname;
    String email;
    String token;
    int district;

    public User(){
        this.id = -1;
        this.name = "";
        this.lastname = "";
        this.email = "";
        this.token = "";
        this.district = -1;
    }

    public User(int id, String name, String lastname, String email, int district){
        this(id, name,lastname,email,"",district);
    }

    public User(int id, String name, String lastname, String email, String token, int district){
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.token = token;
        this.district = district;
    }

    public String getFullName(){
        return name + " " + lastname;
    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }

    public int getDistrict() {
        return district;
    }
}
