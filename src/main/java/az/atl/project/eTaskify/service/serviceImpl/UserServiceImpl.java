package az.atl.project.eTaskify.service.serviceImpl;

import az.atl.project.eTaskify.dto.UserDto;
import az.atl.project.eTaskify.dto.UserLoginRequest;
import az.atl.project.eTaskify.dto.UserResponse;
import az.atl.project.eTaskify.dto.UserResponseList;
import az.atl.project.eTaskify.entity.Authority;
import az.atl.project.eTaskify.entity.SignupRequest;
import az.atl.project.eTaskify.entity.User;
import az.atl.project.eTaskify.exception.OurException;
import az.atl.project.eTaskify.repository.AuthorityRepository;
import az.atl.project.eTaskify.repository.SignupRequestRepository;
import az.atl.project.eTaskify.repository.UserRepository;
import az.atl.project.eTaskify.service.SecurityService;
import az.atl.project.eTaskify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final ModelMapper modelMapper;

    private final SecurityService securityService;

    private final SignupRequestRepository signupRequestRepository;

    @Override
    public UserDto signUp(UserDto userDto, BindingResult br) {
        if (br.hasErrors()){
            throw new OurException(br,null);
        }
        User user = userRepository.findByUsername(userDto.getUsername())
                        .orElseThrow(() -> new OurException(null,"user tapilmadi"));

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> userCheck = userRepository.findByUsername(username);
        String userName = userCheck.get().getUsername();

        if (user.getUsername().equals(userName)) {
            modelMapper.map(userDto, user);
            user.setEnabled(true);
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pass = userDto.getPassword();
            String raw = passwordEncoder.encode(pass);
            user.setPassword(raw);
            userRepository.save(user);
        }
        else {
            throw new OurException(null,"Username sizin deyil");
        }
        //response

        return UserDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .username(user.getUsername())
                .role(userDto.getRole())
                .build();
    }

    @Override
    public UserResponse findAllUser() {
        List<User> list = userRepository.findAll();
        // response
        List<UserResponseList> responseList = new ArrayList<>();
        for (User user : list) {
            UserResponseList userResponseList = new UserResponseList();
            // Burada mapper.map ile Word nesnesini WordResponseList'e dönüştür
            modelMapper.map(user, userResponseList);
            responseList.add(userResponseList);
        }

        UserResponse response = new UserResponse();
        response.setResponses(responseList);
        return response;
    }

    @Override
    public String login(UserLoginRequest userLoginRequest) {
        User user = userRepository.findById(userLoginRequest.getId())
                .orElseThrow(() -> new OurException(null,"User tapilmadi"));
        return user.getName() + " Hesabiniza daxil oldunuz.";
    }

    @Override
    public String requestToBecomeUser(UserDto userDto) {
        if (userDto.getUsername() != null){
            User user = new User();
            user.setUsername(userDto.getUsername());
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String pass = userDto.getPassword();
            String raw = passwordEncoder.encode(pass);
            user.setPassword(raw);
            user.setEnabled(true);
            userRepository.save(user);
            SignupRequest request = new SignupRequest();
            request.setUsername(userDto.getUsername());
            request.setMessageToAdmin("Qeydiyattan kecmek isteyirem");
            signupRequestRepository.save(request);
            return "telebiniz admine catdirildi";
        }
        return "username olmadan role vermek olmur";
    }

    @Override
    public String giveRole(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUsername())
                .orElseThrow(() -> new OurException(null,"bele bir username tapilmadi"));
        Authority authority = new Authority();
        authority.setUsername(userDto.getUsername());
        authority.setAuthority(userDto.getRole());
        authorityRepository.save(authority);
        return "qeydiyattdan kece bilersiz";
    }


}
