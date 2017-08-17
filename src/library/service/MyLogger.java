package library.service;

public class MyLogger implements jdk.internal.instrumentation.Logger {

    @Override
    public void error(String s) {
        System.out.println(s);
    }

    @Override
    public void warn(String s) {
        System.out.println(s);
    }

    @Override
    public void info(String s) {
        System.out.println(s);
    }

    @Override
    public void debug(String s) {
        System.out.println(s);
    }

    @Override
    public void trace(String s) {
        System.out.println(s);
    }

    @Override
    public void error(String s, Throwable throwable) {
        System.out.println(s);
        throwable.printStackTrace();
    }
}
