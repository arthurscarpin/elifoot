package github.arthurscarpin.elifoot.controller;

import github.arthurscarpin.elifoot.config.security.annotations.CanReadClub;
import github.arthurscarpin.elifoot.config.security.annotations.CanWriteClub;
import github.arthurscarpin.elifoot.dto.request.ClubRequest;
import github.arthurscarpin.elifoot.dto.response.ClubDetailResponse;
import github.arthurscarpin.elifoot.dto.response.ClubResponse;
import github.arthurscarpin.elifoot.dto.response.PlayerResponse;
import github.arthurscarpin.elifoot.service.ClubService;
import github.arthurscarpin.elifoot.service.PlayerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    private final PlayerService playerService;

    @CanWriteClub
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClubDetailResponse save(@RequestBody @Valid ClubRequest request) {
        return clubService.save(request);
    }

    @CanReadClub
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<ClubResponse> findAll(Pageable pageable) {
        return clubService.findAll(pageable);
    }

    @CanReadClub
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClubDetailResponse findById(@PathVariable Long id) {
        return clubService.findById(id);
    }

    @CanReadClub
    @GetMapping("/{id}/players")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponse> findByPlayersByClubId(@PathVariable Long id) {
        return playerService.findByClubId(id);
    }

    @CanWriteClub
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClubDetailResponse updateById(@PathVariable Long id, @RequestBody @Valid ClubRequest request) {
        return clubService.updateById(id, request);
    }

    @CanWriteClub
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        clubService.deleteById(id);
    }
}
