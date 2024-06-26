package az.atl.project.eTaskify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
   private String name;
   private String phoneNumber;
   private String address;
   private String email;
   private String password;
   private String username;
   private String role;
}

