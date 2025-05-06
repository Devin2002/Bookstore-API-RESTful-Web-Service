package com.mycompany.testapi.dao;

import com.mycompany.testapi.model.Author;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AuthorDao {
    private static final Map<Integer, Author> authors = new HashMap<>();
    private static int currentId = 1;

    public Author createAuthor(Author author) {
        author.setId(currentId++);
        authors.put(author.getId(), author);
        return author;
    }

    public Collection<Author> getAllAuthors() {
        return authors.values();
    }

    public Author getAuthorById(int id) {
        return authors.get(id);
    }

    public Author updateAuthor(int id, Author updatedAuthor) {
        updatedAuthor.setId(id);
        authors.put(id, updatedAuthor);
        return updatedAuthor;
    }

    public Author deleteAuthor(int id) {
        return authors.remove(id);
    }
}
