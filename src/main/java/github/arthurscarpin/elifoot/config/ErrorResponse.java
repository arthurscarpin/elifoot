package github.arthurscarpin.elifoot.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
public record ErrorResponse(
        LocalDateTime timestamp,
        Integer status,
        String message,

        @JsonInclude(JsonInclude.Include.NON_NULL)
        Map<String, String> errors
        ) {
}
