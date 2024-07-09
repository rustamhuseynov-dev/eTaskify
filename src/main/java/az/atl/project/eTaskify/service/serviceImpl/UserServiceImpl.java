package az.atl.project.eTaskify.service.serviceImpl;

import az.atl.project.eTaskify.dto.*;
import az.atl.project.eTaskify.entity.Authority;
import az.atl.project.eTaskify.entity.SignupRequest;
import az.atl.project.eTaskify.entity.User;
import az.atl.project.eTaskify.exception.OurException;
import az.atl.project.eTaskify.repository.AuthorityRepository;
import az.atl.project.eTaskify.repository.SignupRequestRepository;
import az.atl.project.eTaskify.repository.UserRepository;
import az.atl.project.eTaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final ModelMapper modelMapper;

    private final SignupRequestRepository signupRequestRepository;

    @Override
    public UserResponse signUp(UserRequest userRequest, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        User user = userRepository.findByUsername(userRequest.getUsername())
                        .orElseThrow(() -> new OurException(null,"user tapilmadi"));

        String userName = userCheck().get().getUsername();

        if (user.getUsername().equals(userName)) {
            modelMapper.map(userRequest, user);
            user.setEnabled(true);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pass = userRequest.getPassword();
            String raw = passwordEncoder.encode(pass);
            user.setPassword(raw);
            userRepository.save(user);
        }
        else {
            throw new OurException(null,"Username sizin deyil");
        }
        //response
        return UserResponse.builder()
                .name(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .username(user.getUsername())
                .build();
    }

    public Optional<User> userCheck(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username);
    }

    @Override
    public UserAllResponse findAllUser() {
        List<User> list = userRepository.findAll();
        // response
        List<UserResponseList> responseList = new ArrayList<>();
        for (User user : list) {
            UserResponseList userResponseList = new UserResponseList();
            // Burada mapper.map ile Word nesnesini WordResponseList'e dönüştür
            modelMapper.map(user, userResponseList);
            responseList.add(userResponseList);
        }

        UserAllResponse response = new UserAllResponse();
        response.setResponses(responseList);
        return response;
    }


    @Override
    public String requestToBecomeUser(UserRequest userRequest, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        if (userRequest.getUsername() != null){
            User user = new User();
            user.setUsername(userRequest.getUsername());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pass = userRequest.getPassword();
            String raw = passwordEncoder.encode(pass);
            user.setPassword(raw);
            user.setEnabled(true);
            userRepository.save(user);
            SignupRequest request = new SignupRequest();
            request.setUsername(userRequest.getUsername());
            request.setMessageToAdmin("Qeydiyattan kecmek isteyirem");
            signupRequestRepository.save(request);
            return "telebiniz admine catdirildi";
        }
        return "username olmadan role vermek olmur";
    }

    @Override
    public String giveRole(GiveRoleRequest giveRoleRequest, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        User user = userRepository.findByUsername(giveRoleRequest.getUsername())
                .orElseThrow(() -> new OurException(null,"bele bir username tapilmadi"));
        Authority authority = new Authority();
        authority.setUsername(giveRoleRequest.getUsername());
        authority.setAuthority(giveRoleRequest.getRole());
        authorityRepository.save(authority);
        return "qeydiyattdan kece bilersiz";
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserResponse findById(Long id) {
        User userId = userRepository.findById(id)
                .orElseThrow(() -> new OurException(null,"id tapilmadi"));

        Long user = userCheck().get().getId();
        if (user.equals(id)){
            userRepository.findById(id);
        }
        else {
            throw new OurException(null,"bu user`in melumatlarini gore bilmersen");
        }
        return UserResponse.builder()
                .name(userId.getName())
                .address(userId.getAddress())
                .phoneNumber(userId.getPhoneNumber())
                .username(userId.getUsername())
                .build();
    }

    @Override
    public UserResponse updateUser(UserUpdateRequest userUpdateRequest, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        User user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> new OurException(null,"bele bir user tapilmadi"));
        Authority authority = authorityRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new OurException(null,"bele bir huquqa malik user tapilmadi"));
        Long userId = userCheck().get().getId();
        if (user.getId().equals(userId)){
            dataTransfer(userUpdateRequest,user,authority);
        } else {
            throw new OurException(null,"bu User size aid deyil");
        }
        return UserResponse.builder()
                .username(userUpdateRequest.getUsername())
                .name(userUpdateRequest.getName())
                .phoneNumber(userUpdateRequest.getPhoneNumber())
                .address(userUpdateRequest.getAddress())
                .build();
    }

    @Override
    public UserDeleteResponse deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new OurException(null,"bele bir User tapilmadi"));

            userRepository.deleteById(id);
            authorityRepository.deleteByUsername(user.getUsername());

        //response
        List<UserResponse> list = new ArrayList<>();
        UserResponse userResponse = new UserResponse();
        modelMapper.map(user,userResponse);
        list.add(userResponse);
        return UserDeleteResponse.builder()
                .message("bu User silindi.")
                .responseList(list)
                .build();
    }

    private void dataTransfer(UserUpdateRequest request, User user, Authority authority) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = request.getPassword();
        String raw = passwordEncoder.encode(pass);
        user.setName(request.getName());
        user.setUsername(request.getUsername());
        user.setAddress(request.getAddress());
        user.setPassword(raw);
        user.setPhoneNumber(request.getPhoneNumber());
        userRepository.save(user);
        authority.setUsername(user.getUsername());
        authority.setAuthority(authority.getAuthority());
        authorityRepository.save(authority);
        System.out.println(user);
    }


}
