package com.mycompany.testapi.resource;

import com.mycompany.testapi.dao.BookDao;
import com.mycompany.testapi.dao.AuthorDao;
import com.mycompany.testapi.exception.AuthorNotFoundException;
import com.mycompany.testapi.exception.BookNotFoundException;
import com.mycompany.testapi.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    private BookDao bookDao = new BookDao();
    private AuthorDao authorDao = new AuthorDao();

    
    @POST 
    public Response createBook(Book book) {
        // Validate author exists
        if (authorDao.getAuthorById(book.getAuthorId()) == null) {
            throw new AuthorNotFoundException("Author with ID " + book.getAuthorId() + " not found.");
        }
        Book created = bookDao.createBook(book);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Collection<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBookById(@PathParam("id") int id) {
        Book book = bookDao.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return book;
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") int id, Book updatedBook) {
        // Check if the book exists
        if (bookDao.getBookById(id) == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        // Validate author exists (again)
        if (authorDao.getAuthorById(updatedBook.getAuthorId()) == null) {
            throw new AuthorNotFoundException("Author with ID " + updatedBook.getAuthorId() + " not found.");
        }
        return bookDao.updateBook(id, updatedBook);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") int id) {
        Book deleted = bookDao.deleteBook(id);
        if (deleted == null) {
            throw new BookNotFoundException("Book with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }
}
