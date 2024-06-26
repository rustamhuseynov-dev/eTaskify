package az.atl.project.eTaskify.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExceptionResponse {

    private String message;
    private List<ValidationResponse> validations;
}
