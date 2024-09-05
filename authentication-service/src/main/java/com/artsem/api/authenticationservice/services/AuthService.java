package com.artsem.api.authenticationservice.services;

import com.artsem.api.authenticationservice.exception.DataNotFoundedException;
import com.artsem.api.authenticationservice.model.User;
import com.artsem.api.authenticationservice.model.dto.LoginRequestDto;
import com.artsem.api.authenticationservice.model.dto.LoginResponseDto;
import com.artsem.api.authenticationservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private User register(LoginRequestDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();
        return repository.save(user);
    }

    public LoginResponseDto authenticate(LoginRequestDto dto) {
        User savedUser = getSavedUser(dto);
        return LoginResponseDto.builder()
                .id(savedUser.getId())
                .token(jwtService.generateToken(savedUser))
                .build();
    }

    private User getSavedUser(LoginRequestDto dto) {
        if (!repository.existsByEmail(dto.getEmail())) {
            return register(dto);
        }
        return login(dto);
    }

    private User login(LoginRequestDto dto) {
        User user = repository.findByEmail(dto.getEmail()).orElseThrow(
                () -> new DataNotFoundedException("User not found")
        );
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Password doesn't match");
        }
        return user;
    }

}
