package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.dto.response.PositionResponse;
import github.arthurscarpin.elifoot.enums.Position;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resources")
@RequiredArgsConstructor
public class ResourceController {

    @GetMapping("/positions")
    @ResponseStatus(HttpStatus.OK)
    public List<PositionResponse> getPosition() {
        return Arrays.stream(Position.values())
                .map(position -> new PositionResponse(position.name(), position.getLabel()))
                .toList();
    }
}
