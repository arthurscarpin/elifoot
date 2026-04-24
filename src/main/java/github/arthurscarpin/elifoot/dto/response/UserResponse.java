package github.arthurscarpin.elifoot.dto.response;

import java.util.List;

public record UserResponse(
        String name,
        String email,
        List<String> scopes
) {
}
