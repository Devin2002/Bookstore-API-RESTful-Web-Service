package com.mycompany.testapi.resource;

import com.mycompany.testapi.dao.AuthorDao;
import com.mycompany.testapi.dao.BookDao;
import com.mycompany.testapi.exception.AuthorNotFoundException;
import com.mycompany.testapi.model.Author;
import com.mycompany.testapi.model.Book;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    private AuthorDao authorDao = new AuthorDao();
    private BookDao bookDao = new BookDao();

    @POST
    public Response createAuthor(Author author) {
        Author created = authorDao.createAuthor(author);
        return Response.status(Response.Status.CREATED).entity(created).build();
    }

    @GET
    public Collection<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    public Author getAuthorById(@PathParam("id") int id) {
        Author author = authorDao.getAuthorById(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return author;
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") int id, Author updatedAuthor) {
        if (authorDao.getAuthorById(id) == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return authorDao.updateAuthor(id, updatedAuthor);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") int id) {
        Author deleted = authorDao.deleteAuthor(id);
        if (deleted == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") int id) {
        if (authorDao.getAuthorById(id) == null) {
            throw new AuthorNotFoundException("Author with ID " + id + " not found.");
        }
        return bookDao.getAllBooks()
                      .stream()
                      .filter(book -> book.getAuthorId() == id)
                      .collect(Collectors.toList());
    }
}
