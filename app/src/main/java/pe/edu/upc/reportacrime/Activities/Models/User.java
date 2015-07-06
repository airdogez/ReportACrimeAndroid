package pe.edu.upc.reportacrime.Activities.Models;

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
}
