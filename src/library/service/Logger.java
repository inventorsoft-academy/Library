package library.service;

public class Logger implements jdk.internal.instrumentation.Logger {
    @Override
    public void error(String s) {

    }

    @Override
    public void warn(String s) {

    }

    @Override
    public void info(String s) {
System.out.println(s);
    }

    @Override
    public void debug(String s) {

    }

    @Override
    public void trace(String s) {

    }

    @Override
    public void error(String s, Throwable throwable) {

    }
}
