package Products.Products.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
@Getter
public class GenericException extends Throwable{

    private String message;
    private HttpStatus status;
    public GenericException(String smg) {
        super(smg);
        this.message= smg;
        this.status= HttpStatus.OK;
    }
}
