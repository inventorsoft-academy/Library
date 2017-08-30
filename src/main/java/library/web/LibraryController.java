package library.web;

import library.model.Book;
import library.model.User;
import library.service.BookManager;
import library.service.UserManager;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.OPTIONS;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@AllArgsConstructor
@Controller
@RequestMapping(value = "/library")

@CrossOrigin(origins = "*", methods = {GET, POST, PUT, DELETE, OPTIONS})
public class LibraryController {
    private BookManager bookManager;

    @GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookManager.getAllBooks();
    }

    @GetMapping("/book№/{songId:\\d+}")
    public ResponseEntity<Book> getBookById(@PathVariable Long songId) {
        return bookManager.findById(songId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
