package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.config.security.annotations.CanReadStadium;
import github.arthurscarpin.elifoot.config.security.annotations.CanWriteStadium;
import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.service.StadiumService;
import jakarta.validation.Valid;
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

    @CanWriteStadium
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StadiumResponse save(@RequestBody @Valid StadiumRequest request) {
        return service.save(request);
    }

    @CanReadStadium
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @CanReadStadium
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StadiumResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @CanWriteStadium
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StadiumResponse updateById(@PathVariable Long id, @RequestBody @Valid StadiumRequest request) {
        return service.updateById(id, request);
    }

    @CanWriteStadium
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
