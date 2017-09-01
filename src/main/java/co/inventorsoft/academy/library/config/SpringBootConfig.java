package co.inventorsoft.academy.library.config;

import co.inventorsoft.academy.library.repository.DataBaseRepository;
import co.inventorsoft.academy.library.repository.FileRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringBootConfig {
    @Bean
    public FileRepository fileManager(){
        return new DataBaseRepository();
    }

}
