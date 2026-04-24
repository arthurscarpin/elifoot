package github.arthurscarpin.elifoot.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserRequest(
        @NotBlank
        String name,

        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotNull
        @NotEmpty
        List<Long> scopes
) {
}
