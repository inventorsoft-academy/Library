package library.config;


import library.reprository.filerepro.DataBaseManager;
import library.reprository.filerepro.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringBootConfig {
    @Bean
    public FileManager fileManager(){
        return new DataBaseManager();
    }

}
