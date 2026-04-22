package github.arthurscarpin.elifoot.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

public record ClubDetailResponse(
        Long id,
        String name,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate founded,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        String urlImg,

        StadiumResponse stadium
) {
}
