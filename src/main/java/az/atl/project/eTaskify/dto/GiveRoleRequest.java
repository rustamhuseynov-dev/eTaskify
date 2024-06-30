package az.atl.project.eTaskify.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GiveRoleRequest {

    @NotEmpty(message = "Boş qoymaq olmaz")
    private String username;

    @NotEmpty(message = "Boş qoymaq olmaz")
    private String role;
}
