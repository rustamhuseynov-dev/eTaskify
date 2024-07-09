package az.atl.project.eTaskify.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {

    private long id;

    @Size(min = 3,message = "Minimum 3 karakter olmalıdı")
    @Size(max = 30,message = "Maximum 30 karakter olmalıdı")
    private String name;

    @NotEmpty(message = "Boş qoymaq olmaz")
    private String username;

    @NotEmpty(message = "Boş qoymaq olmaz")
    private String password;

    @Pattern(regexp = "\\(\\d{3}\\)-\\d{3}-\\d{4}", message = "Telefon nömrəsi (000)-000-0000 formatında olmalıdır.")
    private String phoneNumber;

    @Size(min = 2, max = 20, message = "2 ile 20 arasinda ad daxil edin")
    private String address;
}
