package github.arthurscarpin.elifoot.dto.response;

import lombok.Builder;

@Builder
public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
