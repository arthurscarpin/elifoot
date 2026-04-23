package github.arthurscarpin.elifoot.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

public record PlayerDetailResponse(
        Long id,
        String name,
        String position,
        Integer shirtNumber,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg,

        ClubBasicResponse club
) {
}
