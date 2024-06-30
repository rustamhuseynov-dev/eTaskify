package az.atl.project.eTaskify.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserAllResponse {

    private List<UserResponseList> responses;
}
