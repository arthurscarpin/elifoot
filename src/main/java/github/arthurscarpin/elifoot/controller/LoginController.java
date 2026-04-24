package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.dto.request.LoginRequest;
import github.arthurscarpin.elifoot.dto.response.LoginResponse;
import github.arthurscarpin.elifoot.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return service.login(request);
    }
}
