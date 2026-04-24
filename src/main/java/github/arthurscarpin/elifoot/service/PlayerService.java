package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.PlayerRequest;
import github.arthurscarpin.elifoot.dto.response.PlayerDetailResponse;
import github.arthurscarpin.elifoot.dto.response.PlayerResponse;
import github.arthurscarpin.elifoot.entity.Player;
import github.arthurscarpin.elifoot.exceptions.ResourceNotFoundException;
import github.arthurscarpin.elifoot.mapper.PlayerMapper;
import github.arthurscarpin.elifoot.repository.PlayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository repository;

    private final PlayerMapper mapper;

    private final ClubService clubService;

    protected Player searchResource(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Player not found with id: " + id));
    }

    @Transactional
    public PlayerDetailResponse save(PlayerRequest request) {
        Player player = mapper.toEntity(request);
        player.setClub(clubService.searchResource(request.clubId()));
        return mapper.toPlayerDetailResponse(repository.save(player));
    }

    public Page<PlayerResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toPlayerResponse);
    }

    public PlayerDetailResponse findById(Long id) {
        return mapper.toPlayerDetailResponse(searchResource(id));
    }

    public List<PlayerResponse> findByClubId(Long clubId) {
        return repository.findByClubId(clubId)
                .stream()
                .map(mapper::toPlayerResponse)
                .toList();
    }

    @Transactional
    public PlayerDetailResponse updateById(Long id, PlayerRequest request) {
        Player player = searchResource(id);
        player.setClub(clubService.searchResource(request.clubId()));
        player.setId(id);
        player.setName(request.name());
        player.setUrlImg(request.urlImg());
        player.setPosition(request.position());
        player.setShirtNumber(request.shirtNumber());
        return mapper.toPlayerDetailResponse(repository.save(player));
    }

    @Transactional
    public void deleteById(Long id) {
        Player player = searchResource(id);
        repository.deleteById(player.getId());
    }
}
