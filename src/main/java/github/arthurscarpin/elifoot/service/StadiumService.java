package github.arthurscarpin.elifoot.service;

import github.arthurscarpin.elifoot.dto.request.StadiumRequest;
import github.arthurscarpin.elifoot.dto.response.StadiumResponse;
import github.arthurscarpin.elifoot.mapper.StadiumMapper;
import github.arthurscarpin.elifoot.repository.StadiumRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StadiumService {

    private final StadiumRepository repository;

    private final StadiumMapper mapper;

    public Page<StadiumResponse> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toResponse);
    }

    @Transactional
    public StadiumResponse save(StadiumRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }
}
