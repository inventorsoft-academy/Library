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

      /*  CustomList<String> strList= new CustomList<>();

        strList.add("str1");

        strList.add("str2");

        System.out.println("after adding elements size ="+strList.size());

        strList.remove(1);

        System.out.println("after removing element size ="+strList.size());*/

    }

}
