package library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Simple starter with start screen UI.
 *
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        //new LibraryManager().startScreen();
        SpringApplication.run(Main.class,args);
    }
}
