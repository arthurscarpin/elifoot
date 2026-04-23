package github.arthurscarpin.elifoot.dto.request;

import github.arthurscarpin.elifoot.enums.Position;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PlayerRequest(
        @NotBlank
        String name,

        @NotNull
        Position position,

        @NotNull
        Integer shirtNumber,

        String urlImg,

        @NotNull
        Long clubId
) {
}
