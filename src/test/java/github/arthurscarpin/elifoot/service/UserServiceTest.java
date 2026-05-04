package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.UserRequest;
import github.arthurscarpin.elifoot.exceptions.ResourceAlreadyExistsException;
import github.arthurscarpin.elifoot.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        UserRequest request = UserRequest.builder()
                .name("Test User")
                .email("email@test")
                .password("password123")
                .scopes(List.of(1L, 2L, 3L))
                .build();

        when(repository.existsByEmail(request.email())).thenReturn(true);

        ResourceAlreadyExistsException exception =  assertThrows(ResourceAlreadyExistsException.class, () -> {
            service.save(request);
        });
        assertEquals(exception.getMessage(), "User with email " + request.email() + " already exists");
    }
}