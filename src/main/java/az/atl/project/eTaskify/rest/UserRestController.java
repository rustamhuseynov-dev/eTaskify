package az.atl.project.eTaskify.rest;

import az.atl.project.eTaskify.dto.UserDto;
import az.atl.project.eTaskify.dto.UserLoginRequest;
import az.atl.project.eTaskify.dto.UserResponse;
import az.atl.project.eTaskify.dto.UserResponseList;
import az.atl.project.eTaskify.entity.User;
import az.atl.project.eTaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping(path = "/req")
    public ResponseEntity<String> requestToBecomeUser(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.requestToBecomeUser(userDto),HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserDto> signUp(@Valid @RequestBody UserDto userDto, BindingResult br){
        return new ResponseEntity<>(userService.signUp(userDto,br), HttpStatus.CREATED);
    }

    @GetMapping(path = "/login")
    public String login(@RequestBody UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }

    @PostMapping(path = "give-role")
    public ResponseEntity<String> giveRole(@RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.giveRole(userDto),HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-user")
    public ResponseEntity<UserResponse> findAllUser(){
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
    }

}
