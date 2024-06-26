package az.atl.project.eTaskify.exceptionhandler;

import az.atl.project.eTaskify.dto.ExceptionResponse;
import az.atl.project.eTaskify.dto.ValidationResponse;
import az.atl.project.eTaskify.exception.OurException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handle(OurException e) {
        ExceptionResponse r = new ExceptionResponse();
        BindingResult br = e.getBr();
        if (br == null) {

        } else {
            List<FieldError> fieldErrors = br.getFieldErrors();
            List<ValidationResponse> validations = new ArrayList<>();

            for (FieldError error : fieldErrors) {
                ValidationResponse v = new ValidationResponse();
                v.setField(error.getField());
                v.setMessage(error.getDefaultMessage());
                validations.add(v);
            }
            r.setValidations(validations);
        }
        r.setMessage(e.getMessage());

        return r;

    }
}
