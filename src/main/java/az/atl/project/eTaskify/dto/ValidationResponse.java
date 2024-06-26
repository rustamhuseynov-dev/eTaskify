package az.atl.project.eTaskify.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResponse {

    private String field; // bu xetanin hansi deyisende oldugunu gosterir
    private String message; // bu xetanin default message ekranda gosterir

}
