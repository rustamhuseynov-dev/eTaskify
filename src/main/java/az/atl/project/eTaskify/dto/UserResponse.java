package az.atl.project.eTaskify.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private String name;

    private String phoneNumber;

    private String address;

    private String username;
}
