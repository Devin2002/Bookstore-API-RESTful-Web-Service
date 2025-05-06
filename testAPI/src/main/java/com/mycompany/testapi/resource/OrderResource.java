package com.mycompany.testapi.resource;

import com.mycompany.testapi.dao.CartDao;
import com.mycompany.testapi.dao.CustomerDao;
import com.mycompany.testapi.dao.OrderDao;
import com.mycompany.testapi.exception.CartNotFoundException;
import com.mycompany.testapi.exception.CustomerNotFoundException;
import com.mycompany.testapi.model.Cart;
import com.mycompany.testapi.model.Cartitem;
import com.mycompany.testapi.model.Order;
import com.mycompany.testapi.model.Orderitem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {

    private OrderDao orderDao = new OrderDao();
    private CartDao cartDao = new CartDao();
    private CustomerDao customerDao = new CustomerDao();

    @POST
    public Response placeOrder(@PathParam("customerId") int customerId) {
        validateCustomer(customerId);

        Cart cart = cartDao.getCartByCustomerId(customerId);
        if (cart == null || cart.getItems().isEmpty()) {
            throw new CartNotFoundException("Cart is empty or not found for customer ID " + customerId);
        }

        List<Orderitem> orderItems = new ArrayList<>();
        for (Cartitem cartItem : cart.getItems()) {
            Orderitem orderItem = new Orderitem(cartItem.getBookId(), cartItem.getQuantity(), 0.0); // price 0 for now
            orderItems.add(orderItem);
        }

        Order order = orderDao.createOrder(customerId, orderItems);

        cartDao.clearCart(customerId); // After placing order, cart is cleared

        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    public List<Order> getAllOrders(@PathParam("customerId") int customerId) {
        validateCustomer(customerId);
        return orderDao.getOrdersByCustomerId(customerId);
    }

    @GET
    @Path("/{orderId}")
    public Order getOrderById(@PathParam("customerId") int customerId,
                              @PathParam("orderId") int orderId) {
        validateCustomer(customerId);
        Order order = orderDao.getOrderById(customerId, orderId);
        if (order == null) {
            throw new WebApplicationException("Order with ID " + orderId + " not found.", Response.Status.NOT_FOUND);
        }
        return order;
    }

    private void validateCustomer(int customerId) {
        if (customerDao.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }
}
