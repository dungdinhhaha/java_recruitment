package unigap.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import unigap.api.config.filter.JwtTokenUtils;
import unigap.api.dto.in.UserDTO;
import unigap.api.model.User1;
import unigap.api.repository.UserRepository;
import unigap.common.ErrorCode;
import unigap.common.response.ApiException;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils jwtTokenUtil;
    private final AuthenticationManager authenticationManager;


    public User1 createUser(UserDTO userDTO) throws Exception {
        //register user
        String phoneNumber = userDTO.getPhoneNumber();
        // Kiểm tra xem số điện thoại đã tồn tại hay chưa
      userRepository.findByPhoneNumber(phoneNumber).ifPresent(phoneNuber ->{
              throw new RuntimeException();}
      );

        //convert from userDTO => user
        User1 newUser = User1.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(userDTO.getPassword())
                .address(userDTO.getAddress())
                .dateOfBirth(userDTO.getDateOfBirth())
                .facebookAccountId(userDTO.getFacebookAccountId())
                .googleAccountId(userDTO.getGoogleAccountId())
                .active(true)
                .build();

        newUser.setRole("ROLE_ADMIN");
        // Kiểm tra nếu có accountId, không yêu cầu password

            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            newUser.setPassword(encodedPassword);

        return userRepository.save(newUser);
    }


    public String login(
            String phoneNumber,
            String password,
            Long roleId
    ) throws Exception {
        Optional<User1> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if(optionalUser.isEmpty()) {
           throw new RuntimeException();
        }
        //return optionalUser.get();//muốn trả JWT token ?
        User1 existingUser = optionalUser.get();
        //check password
        if (existingUser.getFacebookAccountId() == 0
                && existingUser.getGoogleAccountId() == 0) {
            if(!passwordEncoder.matches(password, existingUser.getPassword())) {
                throw new RuntimeException();
            }
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                phoneNumber, password,
                existingUser.getAuthorities()
        );

        //authenticate with Java Spring security
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(existingUser);
    }
}
