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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StadiumResponse save(@RequestBody StadiumRequest request) {
        return service.save(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<StadiumResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StadiumResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StadiumResponse updateById(@PathVariable Long id, @RequestBody StadiumRequest request) {
        return service.updateById(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
