package library.service;

import library.UImanager.Deleter;
import library.reprository.filerepro.FileManager;
import library.model.Book;
import library.model.User;
import library.reprository.libraryreprository.BookRepro;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class BookManager implements Deleter, BookService {

    private BookRepro bookRepro;

    private List<Book> allBooks;
    private FileManager fileManager;

    public BookManager(BookRepro bookRepro, FileManager fileManager) {
        this.bookRepro = bookRepro;
        this.fileManager = fileManager;
    }

    @PostConstruct
    public void init() {
        allBooks = bookRepro.getAllBooks();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepro.getAllBooks();
    }

    @Override
    public void rentSomeBook(User user, int id) {
        user.readersListOfBooks.add(allBooks.get(id));
        rentStarts(user.readersListOfBooks.get(user.readersListOfBooks.indexOf(allBooks.get(id))));
        allBooks.get(id).setQuantity(allBooks.get(id).getQuantity() - 1);
    }

    @Override
    public void returnSomeBook(User user, int id) {
        allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).setQuantity(
                allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).getQuantity() + 1);
        allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).rentStarts = null;
        allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).rentExpires = null;
        user.readersListOfBooks.remove(user.readersListOfBooks.get(id));
    }

    @Override
    public void rentStarts(Book book) {
        book.rentStarts = new Date();
        book.rentExpires = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(book.rentStarts);
        book.rentStarts = c.getTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(book.rentStarts.toInstant(), ZoneId.systemDefault()).plusDays(7);
        book.rentExpires = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public boolean createNewBook(Book book) {
        if (book.validate().isEmpty()) {
            allBooks.add(book);
            fileManager.saveBooks(allBooks);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        if (allBooks.remove(id) != null) {
            fileManager.saveBooks(allBooks);
            return true;
        }
        return false;
    }

    @Override
    public void update(int id, Book changedBook) {
        bookRepro.update(id,changedBook);
       /* allBooks.get(id).setName(changedBook.getName());
        allBooks.get(id).setAuthor(changedBook.getAuthor());
        allBooks.get(id).setYear(changedBook.getYear());
        allBooks.get(id).setGenre(changedBook.getGenre());
        allBooks.get(id).setQuantity(changedBook.getQuantity());
        fileManager.saveBooks(allBooks);*/
    }

    @Override
    public Optional<Book> findByName(String name) {
        return null;
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepro.findById(id);
    }

    @Override
    public boolean delete(Long id) {
        return bookRepro.delete(id);
    }


}