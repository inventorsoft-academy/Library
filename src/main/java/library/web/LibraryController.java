package library.web;

import library.model.Book;
import library.service.BookManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/book№/{bookId:\\d+}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
        return bookManager.findById(bookId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{bookId:\\d+}")
    public ResponseEntity removeBook(@PathVariable Long bookId) {
        return bookManager.delete(bookId)
                ? new ResponseEntity("Book under ID:"+bookId+" deleted!",HttpStatus.OK)
                : new ResponseEntity("Not found",HttpStatus.NOT_FOUND);
    }



/*    @PutMapping("/update/{bookId:\\d+}")
    public ResponseEntity updateSong(@PathVariable Long bookId,
                                     @RequestBody Book updated) {
        return bookManager.update(bookId, updated)
                ? new ResponseEntity(HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    */

   /* @GetMapping(value = "/search", params = "name")
    public ResponseEntity<Book> getBookByName(@RequestParam String name) {
        return renderBookByName(name);
    }

    private ResponseEntity<Book> renderBookByName(@RequestParam String name) {
        return bookManager.findByName(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }*/
}
