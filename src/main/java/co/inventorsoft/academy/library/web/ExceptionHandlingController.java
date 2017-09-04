package co.inventorsoft.academy.library.web;

import co.inventorsoft.academy.library.common.MyLogger;
import co.inventorsoft.academy.library.common.custom_exception.DBConnectionException;
import co.inventorsoft.academy.library.common.custom_exception.DBWorkException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionHandlingController {
    private MyLogger log = new MyLogger();

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NOT FOUND")
    @ExceptionHandler(DBConnectionException.class)
    public void connectionError(Exception e) {
        log.error(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.EXPECTATION_FAILED, reason = "INTERNAL SERVER ERROR")
    @ExceptionHandler(DBWorkException.class)
    public void workError(Exception e) {
        log.error(e.getMessage());
    }
}
