package pe.edu.upc.reportacrime.Activities.Models;

/**
 * Created by Andres R on 06/07/2015.
 */
public class District {
    private int id;
    private String name;

    public District(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
