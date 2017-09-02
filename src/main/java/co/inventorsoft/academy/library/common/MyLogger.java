package co.inventorsoft.academy.library.common;

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
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(pathToLogs), true));
            writer.append(massage);
            writer.newLine();
            writer.close();
        } catch (IOException ignored) {
        }
    }

    public void info(String massage) {
        write(LocalDateTime.now() + " INFO: " + massage);
    }

    public void warn(String massage) {
        write(LocalDateTime.now() + " WARN: " + className + "  " + massage);
    }

    public void error(String massage) {
        write(LocalDateTime.now() + " ERROR: " + className + " - " + massage);
    }
}