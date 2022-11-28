package vttp.workshopPrac.day21Workshop.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Orders {

    private int orderId;
    private int employeeId;
    private int customerId;
    private String shipName;


    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public String getShipName() {
        return shipName;
    }
    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public static Orders create(SqlRowSet rs) {
        Orders o = new Orders();
        o.setCustomerId(rs.getInt("customer_id"));
        o.setOrderId(rs.getInt("id"));
        o.setEmployeeId(rs.getInt("employee_id"));
        o.setShipName(rs.getString("ship_name"));

        return o;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
                .add("orderId", getOrderId())
                .add("customerId", getCustomerId())
                .add("employeeId", getEmployeeId())
                .add("shipName", getShipName())
                .build();
    }


    
}
