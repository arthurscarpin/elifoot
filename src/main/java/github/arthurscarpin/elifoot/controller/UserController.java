package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.dto.request.UserRequest;
import github.arthurscarpin.elifoot.dto.response.UserResponse;
import github.arthurscarpin.elifoot.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse save(@Valid @RequestBody UserRequest request) {
        return service.save(request);
    }
}
