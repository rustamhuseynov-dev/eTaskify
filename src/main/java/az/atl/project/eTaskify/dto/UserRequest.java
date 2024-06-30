package az.atl.project.eTaskify.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {

   @Size(min = 3,message = "Minimum 3 karakter olmalıdı")
   @Size(max = 30,message = "Maximum 30 karakter olmalıdı")
   private String name;
   @Pattern(regexp = "\\(\\d{3}\\)-\\d{3}-\\d{4}", message = "Telefon nömrəsi (000)-000-0000 formatında olmalıdır.")
   private String phoneNumber;
   @Pattern(regexp = "[a-z]+@[a-z]+\\.[a-z]{2,4}", message = "emaili düz yaz")
   private String email;
   @Size(min = 2, max = 20, message = "2 ile 20 arasinda ad daxil edin")
   private String address;
   @NotEmpty(message = "Boş qoymaq olmaz")
   private String password;
   @NotEmpty(message = "Boş qoymaq olmaz")
   private String username;
}

