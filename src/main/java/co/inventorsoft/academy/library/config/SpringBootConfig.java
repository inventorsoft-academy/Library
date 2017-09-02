package co.inventorsoft.academy.library.config;

import co.inventorsoft.academy.library.repository.UserRepository;
import co.inventorsoft.academy.library.repository.DefaultUserRepository;
import co.inventorsoft.academy.library.repository.DefaultBookRepository;
import co.inventorsoft.academy.library.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringBootConfig {

    @Bean
    public BookRepository userRepository() {
        return new DefaultBookRepository();
    }

    @Bean
    public UserRepository bookRepository() {
        return new DefaultUserRepository();
    }
}
