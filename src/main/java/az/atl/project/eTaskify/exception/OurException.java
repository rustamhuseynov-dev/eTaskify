package az.atl.project.eTaskify.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class OurException extends RuntimeException{
    private BindingResult br;

    // xetani tutur get metodu ile gonderir handler-a cunki o buradan isteyir.
    public OurException(BindingResult br, String m) {
        super(m);
        this.br = br;
    }
}
