package Products.Products.Service;

import Products.Products.Exception.AlreadyRegisteredException;
import Products.Products.Exception.GenericException;
import Products.Products.Exception.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity notFound(NotFoundException notFoundException) {
        return ResponseEntity.status(notFoundException.getStatus()).body(notFoundException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {GenericException.class})
    public ResponseEntity generic(GenericException genericException) {
        return ResponseEntity.status(genericException.getStatus()).body(genericException.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AlreadyRegisteredException.class})
    public ResponseEntity alreadyRegistered(AlreadyRegisteredException e) {
        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
}
