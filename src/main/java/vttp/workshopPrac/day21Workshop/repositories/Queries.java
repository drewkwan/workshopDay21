package vttp.workshopPrac.day21Workshop.repositories;

public class Queries {
    public static final String SQL_GET_ALL_CUSTOMERS = "SELECT * FROM customers LIMIT ? OFFSET ?";
    public static final String SQL_GET_CUSTOMERS_BY_ID ="SELECT * FROM customers WHERE id = ?";
    public static final String SQL_GET_ORDERS_BY_ID ="SELECT * FROM orders where customer_id = ?";
}
