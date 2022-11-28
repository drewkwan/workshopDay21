package vttp.workshopPrac.day21Workshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Customer {

    private String firstName;
    private String lastName;
    private String address;
    private int id;
    private String company;


    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getCompany() {
        return company;
    }
    public void setCompany(String company) {
        this.company = company;
    }

    public static Customer create(SqlRowSet rs) {
        Customer c = new Customer();
        c.setAddress(rs.getString("address"));
        c.setId(rs.getInt("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setCompany(rs.getString("company"));

        return c;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("firstName", getFirstName())
                .add("lastName", getLastName())
                .add("id", getId())
                .add("address", getAddress())
                .add("company", getCompany())
                .build();
    }


    
}
