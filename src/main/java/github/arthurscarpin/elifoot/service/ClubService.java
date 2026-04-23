package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.ClubRequest;
import github.arthurscarpin.elifoot.dto.response.ClubDetailResponse;
import github.arthurscarpin.elifoot.dto.response.ClubResponse;
import github.arthurscarpin.elifoot.entity.Club;
import github.arthurscarpin.elifoot.entity.Stadium;
import github.arthurscarpin.elifoot.exceptions.ResourceNotFoundException;
import github.arthurscarpin.elifoot.mapper.ClubMapper;
import github.arthurscarpin.elifoot.repository.ClubRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClubService {

    private final ClubRepository repository;

    private final ClubMapper mapper;

    private final StadiumService stadiumService;

    protected Club searchResource(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Club not found with id: " + id));
    }

    @Transactional
    public ClubDetailResponse save(ClubRequest request) {
        Club club = mapper.toEntity(request);
        if (Objects.nonNull(club.getStadium())) {
            club.setStadium(stadiumService.searchResource(club.getStadium().getId()));
        }
        club.setCreatedAt(LocalDateTime.now());
        club.setActive(true);
        return mapper.toClubDetailResponse(repository.save(club));
    }

    public Page<ClubResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toClubResponse);
    }

    public ClubDetailResponse findById(Long id) {
        return mapper.toClubDetailResponse(searchResource(id));
    }

    @Transactional
    public ClubDetailResponse updateById(Long id, ClubRequest request) {
        Club club = searchResource(id);
        if (Objects.nonNull(request.stadiumId())) {
            Stadium stadium = stadiumService.searchResource(request.stadiumId());
            club.setStadium(stadium);
        }
        club.setName(request.name());
        club.setFounded(request.founded());
        club.setUrlImg(request.urlImg());
        return mapper.toClubDetailResponse(repository.save(club));
    }

    @Transactional
    public void deleteById(Long id) {
        Club club = searchResource(id);
        repository.deleteById(club.getId());
    }
}
