package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

    public DefaultBookService(BookRepository fileRepository) {
        this.bookRepository = fileRepository;
    }

    @Override
    public boolean saveBooks(Book book) {
        return bookRepository.createBooks(book);
    }

    @Override
    public List<Book> getBooksList() {
        return bookRepository.readBooks();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.readBookById(id);
    }

    @Override
    public boolean updateBook(Long id, Book book) {
        return bookRepository.updateBook(id,book);
    }

    @Override
    public boolean deleteBook(Long id) {
        return bookRepository.deleteBook(id);
    }
}
