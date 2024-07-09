package az.atl.project.eTaskify.service;

import az.atl.project.eTaskify.dto.*;
import az.atl.project.eTaskify.entity.User;
import org.springframework.validation.BindingResult;

import java.util.Optional;

public interface UserService {

    UserResponse signUp(UserRequest userRequest, BindingResult br);

    UserAllResponse findAllUser();

    String requestToBecomeUser(UserRequest userRequest, BindingResult br);

    String giveRole(GiveRoleRequest giveRoleRequest, BindingResult br);

    Optional<User> findByUsername(String username);

    UserResponse findById(Long id);

    UserResponse updateUser(UserUpdateRequest userUpdateRequest, BindingResult br);

    UserDeleteResponse deleteUser(Long id);
}
