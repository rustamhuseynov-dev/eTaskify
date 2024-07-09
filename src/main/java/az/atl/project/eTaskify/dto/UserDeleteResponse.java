package az.atl.project.eTaskify.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserDeleteResponse {

    private String message;

    private List<UserResponse> responseList;
}
