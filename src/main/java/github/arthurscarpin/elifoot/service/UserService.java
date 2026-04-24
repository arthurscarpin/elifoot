package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.UserRequest;
import github.arthurscarpin.elifoot.dto.response.UserResponse;
import github.arthurscarpin.elifoot.entity.Scope;
import github.arthurscarpin.elifoot.entity.User;
import github.arthurscarpin.elifoot.exceptions.ResourceAlreadyExistsException;
import github.arthurscarpin.elifoot.mapper.UserMapper;
import github.arthurscarpin.elifoot.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final ScopeService scopeService;

    private final UserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserResponse save(UserRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new ResourceAlreadyExistsException("User with email " + request.email() + " already exists");
        }

        List<Scope> scopes = request.scopes().stream()
                .map(scopeService::findById)
                .toList();

        User user = mapper.toEntity(request);
        user.setScopes(scopes);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setActive(true);
        return mapper.toResponse(repository.save(user));
    }
}
