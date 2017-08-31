package library.service;

import library.model.Book;
import library.model.User;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();
    void rentSomeBook(User user, int id);
    void returnSomeBook(User user,int id);
    void rentStarts(Book book);
    boolean createNewBook(final Book book);
    void update(int book_id,Book changedBook);
    Optional<Book> findByName(final String name);
    Optional<Book> findById(final Long id);
    boolean delete(Long id);
}
