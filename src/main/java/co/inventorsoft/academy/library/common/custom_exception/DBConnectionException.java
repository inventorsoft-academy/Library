package co.inventorsoft.academy.library.common.custom_exception;

public class DBConnectionException extends RuntimeException {
    public DBConnectionException(String msg) {
        super(msg);
    }

    public DBConnectionException(String msg, Throwable t) {
        super(msg, t);
    }
}
