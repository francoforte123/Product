package Products.Products.Exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.OK)
@Getter
public class NotFoundException extends RuntimeException{

    private String message;
    private HttpStatus status;
    public NotFoundException(String msg) {
        super(msg);
        this.message= msg;
        this.status= HttpStatus.OK;
    }
}
