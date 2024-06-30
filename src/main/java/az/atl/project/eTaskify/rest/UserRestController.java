package az.atl.project.eTaskify.rest;

import az.atl.project.eTaskify.dto.*;
import az.atl.project.eTaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(path = "/req")
    public ResponseEntity<String> requestToBecomeUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.requestToBecomeUser(userRequest),HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest userRequest, BindingResult br){
        return new ResponseEntity<>(userService.signUp(userRequest,br), HttpStatus.CREATED);
    }

    @GetMapping(path = "/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @PostMapping(path = "give-role")
    public ResponseEntity<String> giveRole(@RequestBody GiveRoleRequest giveRoleRequest){
        return new ResponseEntity<>(userService.giveRole(giveRoleRequest),HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-user")
    public ResponseEntity<UserAllResponse> findAllUser(){
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
    }

}
