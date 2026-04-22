package github.arthurscarpin.elifoot.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
public record StadiumResponse(
        Long id,
        String name,
        String city,
        Integer capacity,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg
) {
}
