package github.arthurscarpin.elifoot.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record StadiumRequest(
        @NotBlank
        String name,

        @NotBlank
        String city,

        Integer capacity,
        String urlImg
) {
}
