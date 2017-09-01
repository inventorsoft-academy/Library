package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.Book;

import java.util.List;

public interface BookService {
    void saveBooks(List<Book> allBooksList);

    List<Book> getBooksList();

    Book getBookById(Long id);

    Book updateBook(Long id, Book book);

    Book deleteBook(Long id);
}
