package az.atl.project.eTaskify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
   private String name;
   private String phoneNumber;
   private String address;
   private String email;
   private String password;
   private String username;
}

