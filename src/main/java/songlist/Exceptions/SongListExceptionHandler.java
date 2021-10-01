package songlist.Exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class SongListExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No info for passed param")
    @ExceptionHandler(value = {IllegalArgumentException.class, EntityNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(
            RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Test 1")
    @ExceptionHandler(value = {PropertyValueException.class})
    protected ResponseEntity<Object> handleDatabaseError(
            RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Test 1",
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
