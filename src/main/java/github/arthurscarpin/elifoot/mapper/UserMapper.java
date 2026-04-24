package github.arthurscarpin.elifoot.mapper;

import github.arthurscarpin.elifoot.dto.request.UserRequest;
import github.arthurscarpin.elifoot.dto.response.UserResponse;
import github.arthurscarpin.elifoot.entity.Scope;
import github.arthurscarpin.elifoot.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Collections;
import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeIdsToScopeEntities")
    User toEntity(UserRequest request);

    @Mapping(target = "scopes", source = "scopes", qualifiedByName = "mapScopeEntitiesToStringScopes")
    UserResponse toResponse(User user);

    @Named("mapScopeIdsToScopeEntities")
    default List<Scope> mapScopeIdsToScopeEntities(List<Long> scopeIds) {
        if (scopeIds == null) return Collections.emptyList();

        return scopeIds.stream()
                .map(id -> Scope.builder().id(id).build())
                .toList();
    }

    @Named("mapScopeEntitiesToStringScopes")
    default List<String> mapScopeEntitiesToStringScopes(List<Scope> scopes) {
        if (scopes == null) return Collections.emptyList();

        return scopes.stream()
                .map(Scope::getName)
                .toList();
    }
}
