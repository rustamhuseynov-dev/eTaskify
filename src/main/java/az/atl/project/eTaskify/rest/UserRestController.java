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
    public ResponseEntity<String> requestToBecomeUser(@Valid @RequestBody UserRequest userRequest,BindingResult br){
        return new ResponseEntity<>(userService.requestToBecomeUser(userRequest,br),HttpStatus.OK);
    }

    @PostMapping(path = "/signup")
    public ResponseEntity<UserResponse> signUp(@Valid @RequestBody UserRequest userRequest, BindingResult br){
        return new ResponseEntity<>(userService.signUp(userRequest,br), HttpStatus.CREATED);
    }

    @GetMapping(path = "/login")
    public String login(){
        return "Hesabiniza daxil oldunuz.";
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    @PostMapping(path = "give-role")
    public ResponseEntity<String> giveRole(@Valid @RequestBody GiveRoleRequest giveRoleRequest,BindingResult br){
        return new ResponseEntity<>(userService.giveRole(giveRoleRequest,br),HttpStatus.OK);
    }

    @GetMapping(path = "/get-all-user")
    public ResponseEntity<UserAllResponse> findAllUser(){
        return new ResponseEntity<>(userService.findAllUser(),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest, BindingResult br){
        return new ResponseEntity<>(userService.updateUser(userUpdateRequest,br),HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable Long id){
        return new ResponseEntity<>(userService.deleteUser(id),HttpStatus.OK);
    }

}
