package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.LoginRequest;
import github.arthurscarpin.elifoot.dto.response.LoginResponse;
import github.arthurscarpin.elifoot.entity.User;
import github.arthurscarpin.elifoot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest request) {
        Optional<User> optUser = repository.findByEmail(request.email());

        if (optUser.isEmpty() || !isPasswordCorrect(request.password(), optUser.get().getPassword())) {
            throw new BadCredentialsException("Invalid email or password");
        }

        User savedUser = optUser.get();
        List<String> scopes = savedUser.getScopes().stream()
                .map(scope -> scope.getName())
                .toList();
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("elifoot-api")
                .subject(savedUser.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("email", savedUser.getEmail())
                .claim("scope", scopes)
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return LoginResponse.builder()
                .accessToken(token)
                .expiresIn(expiresIn)
                .build();
    }

    private boolean isPasswordCorrect(String password, String savedPassword) {
        return passwordEncoder.matches(password, savedPassword);
    }
}
