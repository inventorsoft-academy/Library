package co.inventorsoft.academy.library.service;

import co.inventorsoft.academy.library.model.Book;
import co.inventorsoft.academy.library.repository.FileRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BookDefaultService implements BookService {
    private FileRepository fileRepository;

    @PostConstruct
    public void init(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void saveBooks(List<Book> allBooksList) {
        fileRepository.saveBooks(allBooksList);
    }

    @Override
    public List<Book> getBooksList() {
        return fileRepository.getBooksList();
    }

    @Override
    public Book getBookById(Long id) {
        return fileRepository.getBookById(id);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        return fileRepository.updateBook(id, book);
    }

    @Override
    public Book deleteBook(Long id) {
        return fileRepository.deleteBook(id);
    }
}
