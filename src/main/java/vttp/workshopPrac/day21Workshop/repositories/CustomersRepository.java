package vttp.workshopPrac.day21Workshop.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.workshopPrac.day21Workshop.models.Customer;
import vttp.workshopPrac.day21Workshop.models.Orders;

import static vttp.workshopPrac.day21Workshop.repositories.Queries.*;

@Repository
public class CustomersRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    public List<Customer> getAllCustomers(int limit, int offset) {

        //Perform the query
            final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ALL_CUSTOMERS, limit, offset);

            final List<Customer> customers = new LinkedList<>();
            //Attempt to move through the rows
            while (rs.next()) {
                customers.add(Customer.create(rs));
            }
            return customers;
        }

    public List<Customer> getCustomerById(int id) {

        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_CUSTOMERS_BY_ID, id);

        final List<Customer> customers = new LinkedList<>();
        //Attempt to move through the rows
        while (rs.next()) {
            customers.add(Customer.create(rs));
        }
        return customers;
    }


    public List<Orders> getOrdersById(int id) {
        //perform the query
        final SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_ORDERS_BY_ID, id);

        final List<Orders> orders = new LinkedList<>();

        //Scan through
        while (rs.next()) {
            orders.add(Orders.create(rs));
        }
        return orders;
    }

}
