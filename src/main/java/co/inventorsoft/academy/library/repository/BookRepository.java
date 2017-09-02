package co.inventorsoft.academy.library.repository;

import co.inventorsoft.academy.library.model.Book;

import java.util.List;

public interface BookRepository {
    boolean createBooks(Book book);

    List<Book> readBooks();

    Book readBookById(Long id);

    boolean updateBook(Long id, Book book);

    boolean deleteBook(Long id);
}
