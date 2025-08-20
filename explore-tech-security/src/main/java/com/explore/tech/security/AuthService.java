package com.explore.tech.security;

import com.explore.tech.repository.UserRepository;
import com.explore.tech.dto.LoginRequestDto;
import com.explore.tech.dto.LoginResponseDto;
import com.explore.tech.dto.SignupResponseDto;
import com.explore.tech.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User)authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public SignupResponseDto signup(LoginRequestDto loginRequestDto) {
        User user = userRepository.findByUsername(loginRequestDto.getUsername()).orElse(null);

        if(user !=null){
            throw new IllegalArgumentException("User already exists");
        }

        user = userRepository.save(User.builder().username(loginRequestDto.getUsername())
                .password(loginRequestDto.getPassword())
                .build());
        return new SignupResponseDto(user.getId(), user.getUsername());
    }
}
