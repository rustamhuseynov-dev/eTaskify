package az.atl.project.eTaskify.dto;

import az.atl.project.eTaskify.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseList {

    private String name;

    private String username;

    private String email;

    private String phoneNumber;

    private String address;
}
