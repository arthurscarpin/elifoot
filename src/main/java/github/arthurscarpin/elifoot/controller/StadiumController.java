package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.service.StadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stadium")
@RequiredArgsConstructor
public class StadiumController {

    private final StadiumService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StadiumResponse save(@RequestBody StadiumRequest request) {
        return service.save(request);
    }
}
