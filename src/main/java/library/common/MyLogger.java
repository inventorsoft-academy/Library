package library.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class MyLogger {

    private static final String pathToLogs = "src\\main\\resources\\Logs.txt";

    private String className;

    public static MyLogger getCommonClass(Class fromWhere) {
        MyLogger logger = new MyLogger();
        logger.setClassName(fromWhere.getName());
        return logger;
    }

    private void setClassName(String className) {
        this.className = className;
    }

    private void write(String massage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToLogs), true))) {
            writer.append(massage);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void info(String massage) {
            System.out.println(" Info: "+ massage);
    }

    public void warn(String massage) {
        String log = LocalDateTime.now() + " Warn: " + className + "  " + massage;
        System.out.println(" Warn: " + className + "  " + massage);
        write(log);
    }

    public void error(String massage) {
        String log = LocalDateTime.now() + " Error: " + className + " - " + massage;
        System.out.println(" Error: " + className + " - " + massage);
        write(log);
    }


    }





