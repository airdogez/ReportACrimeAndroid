package pe.edu.upc.rac_test1.Activities.Models;

/**
 * Created by Miguel on 15/06/2015.
 */
public class Login {

    private int id;
    private String email;

    public Login(int id, String email) {
        this.id = id;
        this.email = email;
    }

    public Login() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

