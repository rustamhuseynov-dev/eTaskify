package az.atl.project.eTaskify.service;

import az.atl.project.eTaskify.dto.UserDto;
import az.atl.project.eTaskify.dto.UserLoginRequest;
import az.atl.project.eTaskify.dto.UserResponse;
import org.springframework.validation.BindingResult;

public interface UserService {

    UserDto signUp(UserDto userDto, BindingResult br);

    UserResponse findAllUser();

    String login(UserLoginRequest userLoginRequest);

    String requestToBecomeUser(UserDto userDto);

    String giveRole(UserDto userDto);
}
