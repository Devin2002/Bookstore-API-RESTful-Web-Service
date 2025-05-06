package com.mycompany.testapi.resource;

import com.mycompany.testapi.dao.BookDao;
import com.mycompany.testapi.dao.CartDao;
import com.mycompany.testapi.dao.CustomerDao;
import com.mycompany.testapi.exception.BookNotFoundException;
import com.mycompany.testapi.exception.CartNotFoundException;
import com.mycompany.testapi.exception.CustomerNotFoundException;
import com.mycompany.testapi.exception.OutOfStockException;
import com.mycompany.testapi.model.Book;
import com.mycompany.testapi.model.Cart;
import com.mycompany.testapi.model.Cartitem;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {

    private CartDao cartDao = new CartDao();
    private BookDao bookDao = new BookDao();
    private CustomerDao customerDao = new CustomerDao();

    @POST
    @Path("/items")
    public Response addItemToCart(@PathParam("customerId") int customerId, Cartitem item) {
        validateCustomer(customerId);
        validateBook(item.getBookId(), item.getQuantity());

        Cart cart = cartDao.getCartByCustomerId(customerId);
        if (cart == null) {
            cart = cartDao.createCart(customerId);
        }
        cartDao.addItemToCart(customerId, item);

        return Response.status(Response.Status.CREATED).entity(cart).build();
    }

    @GET
    public Cart getCart(@PathParam("customerId") int customerId) {
        validateCustomer(customerId);
        Cart cart = cartDao.getCartByCustomerId(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }
        return cart;
    }

    @PUT
    @Path("/items/{bookId}")
    public Cart updateCartItem(@PathParam("customerId") int customerId,
                               @PathParam("bookId") int bookId,
                               Cartitem item) {
        validateCustomer(customerId);
        validateBook(bookId, item.getQuantity());

        Cart cart = cartDao.getCartByCustomerId(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }
        cartDao.updateCartItem(customerId, bookId, item.getQuantity());
        return cart;
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeCartItem(@PathParam("customerId") int customerId,
                                   @PathParam("bookId") int bookId) {
        validateCustomer(customerId);

        Cart cart = cartDao.getCartByCustomerId(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart not found for customer ID " + customerId);
        }
        cartDao.removeItemFromCart(customerId, bookId);
        return Response.noContent().build();
    }

    private void validateCustomer(int customerId) {
        if (customerDao.getCustomerById(customerId) == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
    }

    private void validateBook(int bookId, int quantity) {
        Book book = bookDao.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        if (book.getStock() < quantity) {
            throw new OutOfStockException("Not enough stock for book ID " + bookId);
        }
    }
}
