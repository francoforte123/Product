package Products.Products.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
public class AlreadyRegisteredException extends RuntimeException{

    private String message;
    public AlreadyRegisteredException(String smg) {
        super(smg);
        this.message= smg;
    }
}
