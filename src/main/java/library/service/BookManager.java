package library.service;

import library.UImanager.Deleter;
import library.reprository.filemanager.FileManager;
import library.reprository.filemanager.TXTFileManager;
import library.model.Book;
import library.model.User;
import library.common.MyLogger;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class BookManager implements Deleter {
    private static final MyLogger log = MyLogger.getCommonClass(BookManager.class);

    private FileManager fileManager = new TXTFileManager();

    private List<Book> allBooks = fileManager.getBooksList();

    public List<Book> getAllBooks() {
        return allBooks;
    }

   public void rentSomeBook(User user,int id) {
                user.readersListOfBooks.add(allBooks.get(id));
                rentStarts(user.readersListOfBooks.get(user.readersListOfBooks.indexOf(allBooks.get(id))));
                allBooks.get(id).setQuantity(allBooks.get(id).getQuantity() - 1);
        }

    public void returnSomeBook(User user,int id) {
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).setQuantity(
                    allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).getQuantity() + 1);
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).rentStarts = null;
            allBooks.get(allBooks.indexOf(user.readersListOfBooks.get(id))).rentExpires = null;
            user.readersListOfBooks.remove(user.readersListOfBooks.get(id));
    }

    private void rentStarts(Book book) {
        book.rentStarts = new Date();
        book.rentExpires = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(book.rentStarts);
        book.rentStarts = c.getTime();
        LocalDateTime ldt = LocalDateTime.ofInstant(book.rentStarts.toInstant(), ZoneId.systemDefault()).plusDays(7);
        book.rentExpires = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }

    public boolean createNewBook(Book book) {
        if (book.validate().isEmpty()) {
            allBooks.add(book);
            fileManager.saveBooks(allBooks);
            return true;
        }  else {
            return false;
    } }

    @Override
    public boolean delete(int id) {
        if(allBooks.remove(id)!=null){
            allBooks.remove(id);
        fileManager.saveBooks(allBooks);
        return true;
        }
        return false;
    }

    public  void update(int book_id,Book changedBook) {
                allBooks.get(book_id).setName(changedBook.getName());
                allBooks.get(book_id).setAuthor(changedBook.getAuthor());
                allBooks.get(book_id).setYear(changedBook.getYear());
                allBooks.get(book_id).setGenre(changedBook.getGenre());
                allBooks.get(book_id).setQuantity(changedBook.getQuantity());
        fileManager.saveBooks(allBooks);
    }


}