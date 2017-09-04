package co.inventorsoft.academy.library.common.custom_exception;

public class DBWorkException extends RuntimeException {
    public DBWorkException(String msg) {
        super(msg);
    }

    public DBWorkException(String msg, Throwable t) {
        super(msg, t);
    }
}
