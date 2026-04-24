package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.config.security.annotations.CanReadPlayer;
import github.arthurscarpin.elifoot.config.security.annotations.CanWritePlayer;
import github.arthurscarpin.elifoot.dto.request.PlayerRequest;
import github.arthurscarpin.elifoot.dto.response.PlayerDetailResponse;
import github.arthurscarpin.elifoot.dto.response.PlayerResponse;
import github.arthurscarpin.elifoot.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService service;

    @CanWritePlayer
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PlayerDetailResponse save(@RequestBody @Valid PlayerRequest request) {
        return service.save(request);
    }

    @CanReadPlayer
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<PlayerResponse> findAll(Pageable pageable){
        return service.findAll(pageable);
    }

    @CanReadPlayer
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerDetailResponse findById(@PathVariable Long id){
        return service.findById(id);
    }

    @CanWritePlayer
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerDetailResponse updateById(@PathVariable Long id, @RequestBody @Valid PlayerRequest request){
        return service.updateById(id, request);
    }

    @CanWritePlayer
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }
}
