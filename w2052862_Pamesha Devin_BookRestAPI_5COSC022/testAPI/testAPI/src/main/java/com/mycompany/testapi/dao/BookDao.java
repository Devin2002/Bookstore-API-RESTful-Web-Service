package com.mycompany.testapi.dao;

import com.mycompany.testapi.model.Book;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class BookDao {
    private static final Map<Integer, Book> books = new HashMap<>();
    private static int currentId = 1;

    public Book createBook(Book book) {
        book.setId(currentId++);
        books.put(book.getId(), book);
        return book;
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Book getBookById(int id) {
        return books.get(id);
    }

    public Book updateBook(int id, Book updatedBook) {
        updatedBook.setId(id);
        books.put(id, updatedBook);
        return updatedBook;
    }

    public Book deleteBook(int id) {
        return books.remove(id);
    }
}
