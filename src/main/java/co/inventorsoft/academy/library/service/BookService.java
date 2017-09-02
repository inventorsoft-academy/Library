package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.Book;

import java.util.List;

public interface BookService {
    boolean saveBooks(Book book);

    List<Book> getBooksList();

    Book getBookById(Long id);

    boolean updateBook(Long id, Book book);

    boolean deleteBook(Long id);
}
