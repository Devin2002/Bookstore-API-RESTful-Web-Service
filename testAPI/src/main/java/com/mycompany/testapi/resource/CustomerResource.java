package com.mycompany.testapi.resource;

import com.mycompany.testapi.dao.CustomerDao;
import com.mycompany.testapi.exception.CustomerNotFoundException;
import com.mycompany.testapi.model.Customer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private CustomerDao customerDao = new CustomerDao();

    @POST
    public Response createCustomer(Customer customer) {
        Customer created = customerDao.createCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Collection<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomerById(@PathParam("id") int id) {
        Customer customer = customerDao.getCustomerById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customer;
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") int id, Customer updatedCustomer) {
        if (customerDao.getCustomerById(id) == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return customerDao.updateCustomer(id, updatedCustomer);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        Customer deleted = customerDao.deleteCustomer(id);
        if (deleted == null) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }
}
