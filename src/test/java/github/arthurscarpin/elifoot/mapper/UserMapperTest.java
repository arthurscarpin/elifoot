package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.UserRequest;
import github.arthurscarpin.elifoot.dto.response.UserResponse;
import github.arthurscarpin.elifoot.entity.Scope;
import github.arthurscarpin.elifoot.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private final UserMapper mapper = Mappers.getMapper(UserMapper.class);

    @Test
    @DisplayName("toEntity - Should map UserRequest to User correctly")
    void toEntity() {
        // Arrange | Given - Construct objects to call the method
        List<Long> scopes = List.of(1L, 2L, 3L);

        UserRequest userRequest = UserRequest.builder()
                .name("User Test")
                .email("user-test@email.com")
                .password("user-test")
                .scopes(scopes)
                .build();

        // Action | When - Call the method being tested
        User user = mapper.toEntity(userRequest);

        // Assertions | Then - Test the results
        assertNotNull(user);
        assertEquals(userRequest.name(), user.getName());
        assertEquals(userRequest.email(), user.getEmail());
        assertEquals(userRequest.password(), user.getPassword());
        assertNotNull(user.getScopes());
        assertEquals(scopes.size(), user.getScopes().size());
    }

    @Test
    @DisplayName("toResponse - Should map User to UserResponse correctly")
    void toResponse() {
        // Arrange | Given - Construct objects to call the method
        Scope scope1 = Scope.builder()
                .id(1L)
                .name("Scope Test 1")
                .build();

        Scope scope2 = Scope.builder()
                .id(1L)
                .name("Scope Test 2")
                .build();

        User user = User.builder()
                .id(1L)
                .name("User Test")
                .email("user-test@email.com")
                .password("user-test")
                .active(true)
                .scopes(List.of(scope1, scope2))
                .build();

        // Action | When - Call the method being tested
        UserResponse userResponse = mapper.toResponse(user);

        // Assertions | Then - Test the results
        assertNotNull(userResponse);
        assertNotNull(userResponse.scopes());

        assertEquals(user.getName(), userResponse.name());
        assertEquals(user.getEmail(), userResponse.email());
        assertEquals(user.getScopes().size(), userResponse.scopes().size());
    }

    @Test
    @DisplayName("mapScopeIdsToScopeEntities - Should map List<Long> to List<Scope> correctly")
    void mapScopeIdsToScopeEntities() {
        // Arrange | Given - Construct objects to call the method
        List<Long> longScopesList = List.of(1L, 2L, 3L);

        // Action | When - Call the method being tested
        List<Scope> scopesList = mapper.mapScopeIdsToScopeEntities(longScopesList);

        // Assertions | Then - Test the results
        assertNotNull(scopesList);
        assertEquals(scopesList.size(), longScopesList.size());
    }

    @Test
    @DisplayName("mapScopeEntitiesToStringScopes - Should map List<Scope> to List<String> correctly")
    void mapScopeEntitiesToStringScopes() {
        // Arrange | Given - Construct objects to call the method
        Scope scope1 = Scope.builder()
                .id(1L)
                .name("Scope Test 1")
                .build();

        Scope scope2 = Scope.builder()
                .id(1L)
                .name("Scope Test 2")
                .build();

        List<Scope> longScopesList = List.of(scope1, scope2);

        // Action | When - Call the method being tested
        List<String> stringScopesList = mapper.mapScopeEntitiesToStringScopes(longScopesList);

        // Assertions | Then - Test the results
        assertNotNull(stringScopesList);
        assertEquals(stringScopesList.size(), longScopesList.size());
    }
}