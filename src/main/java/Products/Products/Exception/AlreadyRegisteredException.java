package Products.Products.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
@Getter
public class AlreadyRegisteredException extends RuntimeException{

    private HttpStatus status;
    private String message;
    public AlreadyRegisteredException(String smg) {
        super(smg);
        this.status = HttpStatus.OK;
        this.message= smg;
    }
}
